package com.example.board.domain.controller;

import com.example.board.domain.dto.PostRequest;
import com.example.board.domain.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// PostController만 테스트 대상으로 지정
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc; // HTTP 요청을 시뮬레이션

    @MockBean
    private PostService postService; // Controller가 의존하는 Service를 Mock으로 대체

    @Autowired
    private ObjectMapper objectMapper; // 객체를 JSON 문자열로 변환

    @Test
    @DisplayName("API 게시글 생성 테스트")
    void createPostApiTest() throws Exception {
        // given
        PostRequest request = new PostRequest("API 테스트", "내용", "작가");

        // postService.create(..)가 호출되면 1L을 반환하도록 설정
        given(postService.create(any(PostRequest.class))).willReturn(1L);

        // when & then
        // POST /api/posts 요청을 시뮬레이션
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON) // JSON 타입으로
                        .content(objectMapper.writeValueAsString(request))) // 요청 본문에 request 객체를 JSON으로 넣어줌
                .andExpect(status().isOk()) // HTTP 상태 코드가 200(OK)인지 확인
                .andExpect(content().string("1")); // 응답 본문이 "1"인지 확인
    }
}
