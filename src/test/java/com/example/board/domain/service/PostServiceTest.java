package com.example.board.domain.service;

import com.example.board.domain.dto.PostRequest;
import com.example.board.domain.entity.Post;
import com.example.board.domain.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// Mockito 확장 기능을 사용합니다.
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository; // 가짜 PostRepository

    @InjectMocks
    private PostService postService; // @Mock으로 만든 객체를 PostService에 주입

    @Test
    @DisplayName("게시글 생성 테스트")
    void createPostTest() {
        // given (주어진 상황)
        PostRequest request = new PostRequest("테스트 제목", "테스트 내용", "글쓴이");

        Post savedPost = Post.builder()
                .id(1L)
                .title("테스트 제목")
                .content("테스트 내용")
                .author("글쓴이")
                .viewCount(0L)
                .build();

        // postRepository.save(..)가 호출되면 savedPost 객체를 반환하라고 설정 (Mocking)
        when(postRepository.save(any(Post.class))).thenReturn(savedPost);

        // when
        Long createdId = postService.create(request);

        // then
        assertThat(createdId).isEqualTo(1L); // 반환된 ID가 1L인지 확인
        verify(postRepository).save(any(Post.class)); // postRepository.save가 호출되었는지 검증
    }

    @Test
    @DisplayName("게시글 ID 조회 테스트")
    void getByIdTest() {
        // given
        Long postId = 1L;
        Post post = Post.builder()
                .id(postId)
                .title("테스트 제목")
                .content("테스트 내용")
                .author("글쓴이")
                .viewCount(0L)
                .build();

        // postRepository.findById(1L)가 호출되면 위에서 만든 post 객체를 반환하라고 설정
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // when
        var response = postService.getById(postId);

        // then
        assertThat(response.id()).isEqualTo(postId);
        assertThat(response.title()).isEqualTo("테스트 제목");
        verify(postRepository).findById(postId); // findById가 1L 인자와 함께 호출되었는지 검증
    }
}