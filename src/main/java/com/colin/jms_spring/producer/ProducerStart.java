package com.colin.jms_spring.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaolz
 * @create 2017-08-23
 */
public class ProducerStart {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("mq_producer.xml");
        Producer producer = (Producer)context.getBean("producerImpl");
        for(int i=0; i<100; i++){
            producer.sendMessage("测试消息" + i);
        }
        context.close();
    }
}
