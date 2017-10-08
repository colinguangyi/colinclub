package com.colin.web.controller;

import com.colin.web.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaolz
 * @create 2017-09-05
 */
@Controller
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/user/{num}")
    public String test(User user, Model model,@PathVariable String num){
        logger.info(user + "---" + num);
        model.addAttribute("name", "world");
        return "hello";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setName("12323");
        user.setId(234243243L);
        map.put("user1", user);
        map.put("user1111", "others");
        return map;
    }

    @RequestMapping("/qrcode")
    public String qrcode(){
        return "qrcode";
    }
}
