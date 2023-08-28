package wiki.wasabi.querydsl.project.domain.board;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.wasabi.querydsl.project.domain.board.dto.req.RegisterBoardRequest;
import wiki.wasabi.querydsl.project.domain.board.dto.res.ReadBoardResponse;
import wiki.wasabi.querydsl.project.domain.board.dto.res.SimpleBoardResponse;

@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(
        final BoardRepository boardRepository
    ) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional
    public Long register(final RegisterBoardRequest registerDto) {
        final Board board = new Board(
            registerDto.title(),
            registerDto.content()
        );

        this.boardRepository.save(board);

        return board.id();
    }

    @Override
    @Transactional
    public ReadBoardResponse read(final Long id) {
        final Board board = this.boardRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        board.increaseView();

        return new ReadBoardResponse(
            board.id(),
            board.title(),
            board.content(),
            board.views()
        );
    }

    @Override
    public Slice<SimpleBoardResponse> readBoardList(final SortType sortType,
                                                    final Pageable pageable) {
        final Slice<Board> boardSlice = sort(sortType, pageable);

        return boardSlice.map(board -> new SimpleBoardResponse(
            board.id(),
            board.title(),
            board.views(),
            board.likes().size(),
            board.createdAt()
        ));
    }

    private Slice<Board> sort(final SortType sortType, final Pageable pageable) {
        return switch (sortType) {
            case LATEST -> boardRepository.findAllByOrderByCreatedAtDesc(pageable);
            case VIEWS -> boardRepository.findAllByOrderByViewsDesc(pageable);
            case LIKES -> boardRepository.findAllByOrderByLikesDesc(pageable);
            case DEFAULT -> boardRepository.findDefaultBoards(pageable);
        };
    }

}
