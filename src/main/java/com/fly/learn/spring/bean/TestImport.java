package com.fly.learn.spring.bean;

import com.fly.learn.spring.postprocess.ShardingDataSourceRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author: peijiepang
 * @date 2020/6/18
 * @Description:
 */
@Component
@Import(ShardingDataSourceRegistrar.class)
public class TestImport {

}
