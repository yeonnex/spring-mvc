package me.yeonnex.springmvc.request;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@Slf4j
public class RequestController {
    @GetMapping("/hello")
    public void hello(
            HttpServletRequest req,
            HttpServletResponse resp,
            HttpMethod httpMethod,
            @RequestHeader MultiValueMap<String, String> headers,
            @RequestHeader("host") String host,
            Locale locale,
            @CookieValue(required = false) String jSessionId
    ) {
        System.out.println("req = " + req + ", resp = " + resp + ", httpMethod = " + httpMethod + ", headers = " + headers + ", host = " + host + ", locale = " + locale + ", jSessionId = " + jSessionId);
    }
}
