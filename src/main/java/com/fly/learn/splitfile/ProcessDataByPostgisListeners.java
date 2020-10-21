package com.fly.learn.splitfile;

import java.util.List;

/**
 * @author: peijiepang
 * @date 2020/10/21
 * @Description:
 */
public class ProcessDataByPostgisListeners extends ReaderFileListener{

    public ProcessDataByPostgisListeners(String encode) {
        this.setEncode(encode);
    }

    @Override
    public void output(List<String> stringList) throws Exception {

    }
}
