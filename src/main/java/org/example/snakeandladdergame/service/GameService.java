package org.example.snakeandladdergame.service;

import org.example.snakeandladdergame.enums.GameStatus;

public interface GameService {
    void play();
    GameStatus getStatus();
}
