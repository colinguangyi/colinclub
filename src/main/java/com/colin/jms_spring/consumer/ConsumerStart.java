package com.colin.jms_spring.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaolz
 * @create 2017-08-23
 */
public class ConsumerStart {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("mq_consumer.xml");
    }
}
