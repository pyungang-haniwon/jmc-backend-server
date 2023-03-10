package com.example.jmcbackend.controller;

import com.example.jmcbackend.member.service.UserServiceImpl;
import com.example.jmcbackend.member.dto.UserJoinRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserServiceImpl userServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {
        String userName = "siyoung";
        String password = "1q2w3e4r";
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    void join_fail() throws Exception {
        String userName = "siyoung";
        String password = "1q2w3e4r";
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isConflict());

    }
}
