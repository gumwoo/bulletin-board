package com.example.board.domain.service;

import com.example.board.domain.dto.*;
import com.example.board.domain.entity.Post;
import com.example.board.domain.repository.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import  java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long create(PostRequest req) {
        Post post = Post.builder()
                .title(req.title())
                .content(req.content())
                .author(req.author())
                .viewCount(0L)
                .build();
        return postRepository.save(post).getId();
    }

    public List<PostResponse>getAll(){
        return postRepository.findAll()
                .stream()
                .map(p -> new PostResponse(
                        p.getId(), p.getTitle(), p.getContent(), p.getAuthor(), p.getViewCount()))
                .toList();
    }
    public PostResponse getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getViewCount());
    }

    @Transactional
    public void update(Long id, PostRequest req) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        post.update(req.title(), req.content());
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void increaseView(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        post.increaseView();
    }
}
