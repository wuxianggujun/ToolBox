package com.wuxianggujun.toolbox.game;

import com.wuxianggujun.toolbox.engine.GameEngine;
import com.wuxianggujun.toolbox.engine.IGameLogic;

public class Main {


    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new DummyGame();
            GameEngine gameEng = new GameEngine("GAME", 600, 480, vSync, gameLogic);
            gameEng.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}