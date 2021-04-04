package chess.domain.gamestate;

import chess.domain.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {
    private State ready;

    @BeforeEach
    void setUp() {
        ready = new Ready(Board.getGamingBoard());
    }

    @Test
    @DisplayName("start 메서드 테스트")
    void startTest() {
        assertThat(ready.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("finished 메서드 테스트")
    void finishedTest() {
        assertThat(ready.finished()).isInstanceOf(Finished.class);
    }
}