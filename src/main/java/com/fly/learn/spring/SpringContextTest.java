package com.fly.learn.spring;

import com.fly.learn.spring.bean.TestB;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: peijiepang
 * @date 2020-05-06
 * @Description:
 */
public class SpringContextTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        TestB testB = applicationContext.getBean(TestB.class);
        Assert.assertNotNull(testB.getTestA());
    }

}
