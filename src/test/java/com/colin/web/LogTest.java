package com.colin.web;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaolz
 * @create 2017-09-12
 */
public class LogTest {
//    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test01() {
        StringBuffer toLover = new StringBuffer();
        toLover.append("                         __________----__________                          ").append("\n");
        toLover.append("                        /                        \\                        ").append("\n");
        toLover.append("           ------------|__________________________|------------            ").append("\n");
        toLover.append("    -------|-----------\\                         /------------|--------   ").append("\n");
        System.out.println(toLover.toString());
    }
}
