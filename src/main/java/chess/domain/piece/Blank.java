package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMethodCallException;
import chess.exception.PieceDoesNotExistException;

import java.util.List;

public class Blank extends Piece {
    private static final String BLANK_INITIAL = ".";
    private static final Blank CACHED_BLANK = new Blank();

    public Blank() {
        super(Side.NONE, BLANK_INITIAL);
    }

    public static Blank getBlank() {
        return CACHED_BLANK;
    }

    @Override
    protected boolean movable(int rowDifference, int columnDifference) {
        throw new PieceDoesNotExistException();
    }

    @Override
    protected List<Position> getRoute(Position from, Position to) {
        throw new PieceDoesNotExistException();
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public double score() {
        throw new PieceDoesNotExistException();
    }
}
