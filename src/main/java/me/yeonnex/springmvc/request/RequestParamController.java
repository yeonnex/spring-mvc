package me.yeonnex.springmvc.request;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import me.yeonnex.springmvc.HelloData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {
    @GetMapping("/request-param/v1")
    public String paramV1(HttpServletRequest req) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        log.info("username={} page={}", username, age);

        return "index";
    }

    @ResponseBody
    @GetMapping("/request-param/v2")
    public String paramV2(@RequestParam("username") String username,
                          @RequestParam("age") Integer age) {
        log.info("username={} page={}", username, age);

        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param/v3")
    public String paramV3(String username, Integer age) {
        log.info("username={} page={}", username, age);

        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param-map")
    public String paramMap(@RequestParam Map<String, String> paramMap) {
        paramMap.forEach((k,v) -> log.info("key: {} value: {}", k, v));

        return "ok";
    }

    // http://localhost:8080/model-attribute-v1?username=yeon&age=14
    @ResponseBody
    @GetMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData data){
        System.out.println(data);
        return "ok";
    }
}
