package com.fly.learn.designpattern.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2018/11/5
 * @Description:
 */
public abstract class CommonTemplate {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonTemplate.class);

    public void create(){
        LOGGER.info("刚开始执行1");
        this.test1();
        LOGGER.info("刚开始执行2");
        this.test2();
        LOGGER.info("执行完成");
    }

    abstract void test1();

    abstract void test2();

}
