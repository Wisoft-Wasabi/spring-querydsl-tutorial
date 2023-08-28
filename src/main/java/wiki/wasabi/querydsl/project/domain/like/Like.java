package wiki.wasabi.querydsl.project.domain.like;

import jakarta.persistence.*;
import wiki.wasabi.querydsl.project.domain.board.Board;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "board_id",
        referencedColumnName = "id"
    )
    private Board board;

    protected Like() {}

    public Like(
        final Board board
    ) {
        this.board = board;
    }

}
