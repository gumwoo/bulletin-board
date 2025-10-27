package com.example.board.domain.controller;

import com.example.board.domain.dto.PostRequest;
import com.example.board.domain.dto.PostResponse;
import com.example.board.domain.entity.Post;
import com.example.board.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public Long create(@Valid @RequestBody PostRequest req) {
        return postService.create(req);
    }

    @GetMapping
    public List<PostResponse> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public PostResponse getById(@PathVariable Long id) {
        postService.increaseView(id);
        return postService.getById(id);
    }

    @PutMapping("/{id}")
    public PostResponse update(@PathVariable Long id, @Valid @RequestBody PostRequest req) {
        postService.update(id, req);
        return postService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
