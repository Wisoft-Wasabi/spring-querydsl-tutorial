package wiki.wasabi.querydsl.project.domain.member;

import jakarta.persistence.*;
import wiki.wasabi.querydsl.project.domain.board.Board;

import java.util.Set;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private Set<Board> boards;


}
