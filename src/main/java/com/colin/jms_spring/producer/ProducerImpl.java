package com.colin.jms_spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author zhaolz
 * @create 2017-08-23
 */
public class ProducerImpl implements Producer{
    @Autowired
    JmsTemplate jmsTemplate;

    @Resource(name="queueDestination")//此处用resource是因为可能配置多个destination
    Destination destination;

    public void sendMessage(final String msg) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage text = session.createTextMessage(msg);
                System.out.println("发送消息：" + msg);
                return text;
            }
        });
    }
}
