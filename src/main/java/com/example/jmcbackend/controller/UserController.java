package com.example.jmcbackend.controller;

import com.example.jmcbackend.service.UserService;
import com.example.jmcbackend.domain.dto.UserJoinRequest;
import com.example.jmcbackend.domain.dto.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

//    @PostMapping("/join")
//    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto) {
//        userService.join(dto.getUserName(), dto.getPassword());
//        return ResponseEntity.ok().body("회원가입이 성공했습니다.");
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto) {
//        String token = userService.login(dto.getUserName(), dto.getPassword());
//        return ResponseEntity.ok().body(token);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok().body(userService.login("", ""));
    }
}
