package com.example.board.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank @Size(max = 120) String title,
        @NotBlank String content,
        @NotBlank @Size(max = 50) String author
) {
}
