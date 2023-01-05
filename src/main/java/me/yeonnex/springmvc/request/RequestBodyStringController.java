package me.yeonnex.springmvc.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@RestController
@Slf4j
public class RequestBodyStringController {

    // HttpServletRequeest - getInputStream()
    @PostMapping("/request-body/v1")
    public void requestBodyStringV1(HttpServletRequest req) throws IOException {
        ServletInputStream inputStream = req.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody {}", messageBody);
    }

    @PostMapping("/request-body/v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body/v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String body = httpEntity.getBody();
        log.info("messageBody={}", body);

        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body/v4")
    public ResponseEntity<String> requestBodyStringV4(RequestEntity<String> request) {
        String body = request.getBody();
        log.info("messageBody={}", body);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/request-body/v5")
    public String requestBodyStringV5(@RequestBody String body) {
        log.info("messageBody={}", body);
        return "ok";
    }
}
