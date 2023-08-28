package wiki.wasabi.querydsl.project.domain.board.dto.res;

public record ReadBoardResponse(
    Long id,
    String title,
    String content,
    Long views
) {
}
