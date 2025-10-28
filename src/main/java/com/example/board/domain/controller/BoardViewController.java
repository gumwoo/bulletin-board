package com.example.board.domain.controller;



import com.example.board.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardViewController {
    private final PostRepository postRepository;

    @GetMapping("/board")
    public String showboard(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "list";
    }
    @GetMapping("/board/write")
    public String showWritePage() {
        return "write";
    }

    @GetMapping("/board/detail")
    public String showDetailPage(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        return "detail";
    }
}
