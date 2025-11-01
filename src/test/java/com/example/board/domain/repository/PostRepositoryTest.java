package com.example.board.domain.repository;

import com.example.board.domain.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

// JPA 관련 빈(Bean)들만 로드하여 테스트
@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("Post 저장 및 조회 테스트")
    void saveAndFindByIdTest() {
        // given
        Post post = Post.builder()
                .title("레포지토리 테스트")
                .content("내용입니다.")
                .author("테스터")
                .viewCount(0L)
                .build();

        // when
        Post savedPost = postRepository.save(post);
        Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);

        // then
        assertThat(foundPost).isNotNull(); // 조회된 객체가 null이 아닌지 확인
        assertThat(foundPost.getId()).isEqualTo(savedPost.getId()); // ID가 일치하는지 확인
        assertThat(foundPost.getTitle()).isEqualTo("레포지토리 테스트"); // Title이 일치하는지 확인
    }
}