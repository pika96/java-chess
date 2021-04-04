package chess;

import chess.dto.PositionDTO;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) throws SQLException {
        port(8084);
        final Gson GSON = new Gson();
        JsonTransformer jsonTransformer = new JsonTransformer();

        ChessService chessService = new ChessService();
        staticFiles.location("/public");
        chessService.initChessGame();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        post("/restart", (req, res) -> {
            chessService.initChessGame();
            return "보드 초기화 성공!";
        }, jsonTransformer);
        post("/move", (req, res) -> {
            PositionDTO positionDTO = GSON.fromJson(req.body(), PositionDTO.class);
            return chessService.move(positionDTO);
        }, jsonTransformer);
        post("/currentBoard", (req, res) -> {
            return chessService.getCurrentBoard();
        }, jsonTransformer);
        post("/currentTurn", (req, res) -> {
            return chessService.turnName();
        }, jsonTransformer);

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}