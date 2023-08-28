package wiki.wasabi.querydsl.project.domain.board.dto.req;

import jakarta.validation.constraints.NotBlank;

public record RegisterBoardRequest(
    @NotBlank
    String title,
    @NotBlank
    String content
) {
}
