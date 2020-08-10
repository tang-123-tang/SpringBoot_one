package com.tjj.javaSpringBootOne.test.controller;

import com.tjj.javaSpringBootOne.test.vo.ApplicationText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TextController {
private final static Logger LOGGER=LoggerFactory.getLogger(TextController.class);
    @Value("${server.port}")
    private int port;
    @Value("${com.age}")
    private int age;
    @Value("${com.desc}")
    private String desc;
    @Value("${com.name}")
    private String name;
    @Value("${com.random}")
    private String random;
@Autowired
    ApplicationText applicationText;
    /**
     * 127.0.0.1:8085/test/testConfig
     *
     * @return
     */
    @GetMapping("/testConfig")
    @ResponseBody
    public String configTest() {

        return new StringBuffer().append(port).append("------")
                .append(age).append("------")
                .append(name).append("------")
                .append(desc).append("------")
                .append(random).append("------").append("<br>")
                .append(applicationText.getPort()).append("------")
                .append(applicationText.getName()).append("------")
                .append(applicationText.getDesc()).append("------")
                .append(applicationText.getRandom()).append("------").toString();

    }

    /**
     * 127.0.0.1:8080/test/test/testDesc
     *
     * @return
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc() {
        return "that was my testDesc";
    }
   @GetMapping("/testLog")
    @ResponseBody
    public String testLog(){
       LOGGER.trace("that was a  trace log");
       LOGGER.debug("that was a  debug log");
       LOGGER.info("that was a  info log");
       LOGGER.warn("that was a  warn log");
       LOGGER.error("that was a  error log");
               return "that was a testLog";
    }

}
