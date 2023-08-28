package wiki.wasabi.querydsl.project.domain.board;

import jakarta.persistence.*;
import wiki.wasabi.querydsl.project.domain.like.Like;
import wiki.wasabi.querydsl.project.domain.member.Member;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Long views;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(
        name = "writer_id",
        referencedColumnName = "id"
    )
    private Member member;

    @OneToMany(mappedBy = "board")
    private Set<Like> likes;

    protected Board() {}

    public Board(
        final String title,
        final String content
    ) {
        this.title = title;
        this.content = content;
        this.views = 0L;
        this.createdAt = LocalDateTime.now();
        this.likes = new HashSet<>();
    }

    public Long id() {
        return this.id;
    }

    public String title() {
        return this.title;
    }

    public String content() {
        return this.content;
    }

    public Long views() {
        return this.views;
    }

    public void increaseView() {
        this.views++;
    }

    public Set<Like> likes() {
        return this.likes;
    }

    public LocalDateTime createdAt() {
        return this.createdAt;
    }

}
