package com.wuxianggujun.toolbox.engine;

public class GameEngine implements Runnable {

    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Timer timer;

    private final IGameLogic gameLogic;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        this.timer = new Timer();
    }


    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getELapsedTime();
            accumulator += elapsedTime;
            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();
            if (!window.isvSync()) {
                sync();
            }

        }
    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void render() {
        gameLogic.render(window);
        window.update();

    }

    private void update(float interval) {
        gameLogic.update(interval);
    }

    private void input() {
        gameLogic.input(window);
    }

    protected void init() throws Exception {
        window.init();
        timer.init();
        gameLogic.init();
    }
}
