package com.tjj.javaSpringBootOne.modules.test.controller;

import com.tjj.javaSpringBootOne.modules.test.entity.City;
import com.tjj.javaSpringBootOne.modules.test.entity.Student;
import com.tjj.javaSpringBootOne.modules.test.service.CityService;
import com.tjj.javaSpringBootOne.modules.test.vo.ApplicationText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@Autowired
    CityService cityService;

    /**
     *127.0.0.1/test/index
     */
@GetMapping("/index")
    public String testIndexPage(ModelMap modelMap){
    modelMap.addAttribute("tempate","test/index") ;
    //返回外层的碎片组装器
     String msg="<h1>我是h1</h1>";
    modelMap.addAttribute("msg",msg);
    modelMap.addAttribute("a",2);
    modelMap.addAttribute("b",2);
    modelMap.addAttribute("flag",true);
    modelMap.addAttribute("flag2",false);
    Student student=new Student();
    student.setStudentName("wangwu ");
    student.setStudentId(23);
    modelMap.addAttribute("student",student);
    modelMap.addAttribute("baiduURL","http://www.baidu.com");
    modelMap.addAttribute("imgPath","https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1830914723,3154965800&fm=26&gp=0.jpg");
    modelMap.addAttribute("valueP","one");
    List<City> cities = cityService.getCitiesByCountryId(522);
    modelMap.addAttribute("cities",cities);
    modelMap.addAttribute("Cityurl","/api/city");
    modelMap.addAttribute("city",cities.get(0));

    return "index";
    }
@GetMapping("/index2")
    public String testIndexPage2(ModelMap modelMap){
    modelMap.addAttribute("tempate","test/index2") ;
    //返回外层的碎片组装器

    return "index";
    }



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
     * 127.0.0.1/test/testDesc?paramKey=fuck
     *
     * @return
     */
    @GetMapping("/testDesc")
    @ResponseBody
    public String testDesc(HttpServletRequest request,
                           @RequestParam(value = "paramKey") String paramValue) {
        String paramValue2=request.getParameter("paramKey");

        return "that was my testDesc"+paramValue+"===="+paramValue2;
    }

    /**
     * 127.0.0.1:8085/test/testLog
     * @return
     */
   @GetMapping("/testLog")
    @ResponseBody
    public String testLog(){
       LOGGER.trace("that was a  trace log");
       LOGGER.debug("that was a  debug log");
       LOGGER.info("that was a  info log");
       LOGGER.warn("that was a  warn log");
       LOGGER.error("that was a  error log");
               return "that was my page";
    }

}
