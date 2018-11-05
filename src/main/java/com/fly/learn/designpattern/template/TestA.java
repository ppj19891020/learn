package com.fly.learn.designpattern.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: peijiepang
 * @date 2018/11/5
 * @Description:
 */
public class TestA extends CommonTemplate{
    private final static Logger LOGGER = LoggerFactory.getLogger(TestA.class);

    @Override
    void test1() {
        LOGGER.info("TestA test1");
    }

    @Override
    void test2() {
        LOGGER.info("TestA test2");
    }
}
