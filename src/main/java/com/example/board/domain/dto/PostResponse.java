package com.example.board.domain.dto;

public record PostResponse(
        Long id,
        String title,
        String content,
        String author,
        Long viewCount
) {

}
