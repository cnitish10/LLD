package org.example.snakeandladdergame.service;

import org.example.snakeandladdergame.enums.GameStatus;
import org.example.snakeandladdergame.model.Board;
import org.example.snakeandladdergame.model.Cell;
import org.example.snakeandladdergame.model.Jump;
import org.example.snakeandladdergame.model.Player;

import java.util.Queue;
import java.util.Random;

public class GameServiceImpl implements GameService{
    private Board board;
    private Queue<Player> players;
    private Random dice;
    private GameStatus status;

    public GameServiceImpl(Board board, Queue<Player> players) {
        this.board = board;
        this.players = players;
        this.dice = new Random();
        this.status = GameStatus.NOT_STARTED;
    }

    @Override
    public void play() {
        status = GameStatus.IN_PROGRESS;
        while (status == GameStatus.IN_PROGRESS) {
            Player current = players.poll();
            int roll = dice.nextInt(6) + 1;
            int newPos = current.getPosition() + roll;

            if (newPos > board.getTotalCells()) {
                System.out.println(current.getName() + " rolled " + roll + " but cannot move.");
            } else {
                Cell cell = board.getCell(newPos);
                if (cell.getJump() != null) {
                    Jump jump = cell.getJump();
                    switch (jump.getType()) {
                        case SNAKE:
                            System.out.println("🐍 " + current.getName() + " bitten at " + newPos + " → " + jump.getEnd());
                            break;
                        case LADDER:
                            System.out.println("🪜 " + current.getName() + " climbed at " + newPos + " → " + jump.getEnd());
                            break;
                    }
                    newPos = jump.getEnd();
                }
                current.setPosition(newPos);
                System.out.println(current.getName() + " rolled " + roll + " and moved to " + newPos);

                if (newPos == board.getTotalCells()) {
                    System.out.println("🎉 " + current.getName() + " wins the game!");
                    status = GameStatus.FINISHED;
                    break;
                }
            }
            players.add(current);
        }
    }

    @Override
    public GameStatus getStatus() {
        return status;
    }
}
