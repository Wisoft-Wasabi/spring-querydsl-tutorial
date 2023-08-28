package wiki.wasabi.querydsl.project.domain.board;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wiki.wasabi.querydsl.project.domain.board.dto.req.RegisterBoardRequest;
import wiki.wasabi.querydsl.project.domain.board.dto.res.ReadBoardResponse;
import wiki.wasabi.querydsl.project.domain.board.dto.res.SimpleBoardResponse;

@RequestMapping("/boards")
@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(
        final BoardService boardService
    ) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody @Valid final RegisterBoardRequest registerDto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.boardService.register(registerDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadBoardResponse> read(@PathVariable("id") @Valid final Long id) {
        return ResponseEntity.ok(this.boardService.read(id));
    }

    @GetMapping
    public ResponseEntity<Slice<SimpleBoardResponse>> readBoardList(
        @RequestParam(name = "sortBy", defaultValue = "default") @Valid final String sortBy,
        @PageableDefault(size = 3) final Pageable pageable) {
        return ResponseEntity.ok(this.boardService.readBoardList(SortType.valueOf(sortBy.toUpperCase()), pageable));
    }

}
