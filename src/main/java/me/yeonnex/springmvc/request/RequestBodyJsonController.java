package me.yeonnex.springmvc.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yeonnex.springmvc.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {
 * "username": "yeon",
 * "age": 24
 * }
 * <br><br/>
 * content-type: application/json
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class RequestBodyJsonController {
    private final ObjectMapper objectMapper;

    @PostMapping("/request-body-json/v1")
    public void jsonV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String data = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(data, HelloData.class);
        log.info(helloData.toString());
        resp.getWriter().write("ok");
    }

    @PostMapping("/request-body-json/v2")
    public String jsonV2(@RequestBody String data) throws JsonProcessingException {
        HelloData helloData = objectMapper.readValue(data, HelloData.class);
        log.info(helloData.toString());
        return "ok";
    }

    @PostMapping("/request-body-json/v3")
    public HelloData jsonV3(@RequestBody HelloData data) {
        log.info(data.toString());
        return data;
    }

    @PostMapping("/request-body-json/v4")
    public ResponseEntity<HelloData> jsonV4(@RequestBody HelloData data) {
        log.info(data.toString());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
