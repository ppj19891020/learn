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
        LOGGER.info("1");
        this.test1();
        LOGGER.info("2");
        this.test2();
        LOGGER.info("ִ3");
    }

    abstract void test1();

    abstract void test2();

}
