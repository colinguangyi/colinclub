package com.colin.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhaolz
 * @create 2017-08-29
 */
public class ClusterStart {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        JedisClientCluster client = (JedisClientCluster)context.getBean("jedisClientCluster");
        client.set("test1", "test1");
        System.out.println(client.get("test1"));
    }
}
