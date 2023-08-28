package wiki.wasabi.querydsl.project.domain.board;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import wiki.wasabi.querydsl.project.domain.board.dto.req.RegisterBoardRequest;
import wiki.wasabi.querydsl.project.domain.board.dto.res.ReadBoardResponse;
import wiki.wasabi.querydsl.project.domain.board.dto.res.SimpleBoardResponse;

public interface BoardService {

    Long register(RegisterBoardRequest registerDto);
    ReadBoardResponse read(Long id);

    Slice<SimpleBoardResponse> readBoardList(SortType sortType, Pageable pageable);

}
