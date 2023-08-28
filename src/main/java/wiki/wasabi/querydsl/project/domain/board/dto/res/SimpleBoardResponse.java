package wiki.wasabi.querydsl.project.domain.board.dto.res;

import java.time.LocalDateTime;

public record SimpleBoardResponse(
    Long id,
    String title,
    Long views,
    int likeCount,
    LocalDateTime createdAt
) {
}
