package me.yeonnex.springmvc.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/members")
public class MappingController {
    @GetMapping("/{memberId}")
    public void getMember(@PathVariable String memberId) {
        log.info("memberId = {}", memberId);
    }

    @PostMapping
    public void register() {
        log.info("register member");
    }

    @PatchMapping("/{memberId}")
    public void update() {
        log.info("update member");
    }

    @DeleteMapping("/{memberId}")
    public void delete() {
        log.info("delete member");
    }
}
