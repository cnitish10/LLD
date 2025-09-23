package org.example.snakeandladdergame;

import org.example.snakeandladdergame.model.Board;
import org.example.snakeandladdergame.model.Jump;
import org.example.snakeandladdergame.model.Player;
import org.example.snakeandladdergame.service.GameService;
import org.example.snakeandladdergame.service.GameServiceImpl;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadderMainApp {
    public static void main(String args[]){
        Board board = new Board(10,10);
        // Add snakes
        board.addJump(new Jump(95, 25));
        board.addJump(new Jump(90, 50));
        board.addJump(new Jump(70, 30));

        // Add ladders
        board.addJump(new Jump(2, 20));
        board.addJump(new Jump(15, 40));
        board.addJump(new Jump(60, 85));

        // Players
        Queue<Player> players = new LinkedList<>();
        players.add(new Player("Nitish"));
        players.add(new Player("Manish"));

        GameService gameService = new GameServiceImpl(board, players);
        gameService.play();
    }
}
