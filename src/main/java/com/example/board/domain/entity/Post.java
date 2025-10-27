package com.example.board.domain.entity;
import lombok.*;
import jakarta.persistence.*;
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private Long viewCount;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseView(){
        this.viewCount++;
    }
}
