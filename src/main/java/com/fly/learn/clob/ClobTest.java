package com.fly.learn.clob;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 大文件插入数据库
 * @author: peijiepang
 * @date 2020/10/26
 * @Description:
 */
public class ClobTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ClobTest.class);
    private static DataSource dataSource;
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    public static void main(String[] args) throws Exception{
        //创建Properties对象，用于加载配置文件
        Properties pro = new Properties();
        //加载配置文件
        pro.load(ClobTest.class.getClassLoader().getResourceAsStream("druid.properties"));
        //获取数据库连接池对象
        dataSource = DruidDataSourceFactory.createDataSource(pro);

        int size = 1024*1024;
        int loopSize = 100000 - 17144;
        long start = System.currentTimeMillis();
        List<Future<Boolean>> list = new ArrayList<>(loopSize);
        // 1k 数据
        StringBuffer temp = new StringBuffer();
        for(int i=0;i<1000;i++){
            temp.append('a');
        }
        StringBuffer bigText = new StringBuffer();
        for(int i=0;i<size/1024;i++){
            bigText.append(temp);
        }

        LOGGER.info("big text:{}",bigText.length());
        for(int i=0;i<loopSize;i++){
            list.add(executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    insertString(bigText.toString());
                    return true;
                }
            }));
        }
        for(int i=0;i<list.size();i++){
            list.get(i).get();
        }
        executorService.shutdown();
        long end = System.currentTimeMillis();
        LOGGER.info("插入时间:{}",end - start);
    }

    /**
     * 查询大字段
     * @throws Exception
     */
    public static String query() throws Exception{
        StringBuilder result = new StringBuilder();
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = dataSource.getConnection();
            String sql="select html from insight_sync.drug_html limit 1";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
               result.append(rs.getString("html"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs.close();
        ps.close();
        conn.close();
        return result.toString();
    }

    /**
     * 插入大文本
     * @throws Exception
     */
    public static void insertString(String longText) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            long start = System.currentTimeMillis();
            String sql = "insert into source.big_table_test_source(pic1,pic2,pic3,pic4) VALUES(?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, longText);
            ps.setString(2, "");
            ps.setString(3, "");
            ps.setString(4, "");
            ps.executeUpdate();
            long end = System.currentTimeMillis();
            LOGGER.info("thread-name:{} 耗时:{}",Thread.currentThread().getName(),end-start);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ps.close();
        conn.close();
    }

}
