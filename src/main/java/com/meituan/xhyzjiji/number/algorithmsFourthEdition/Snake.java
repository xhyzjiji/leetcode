package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Snake {

    public static class RetroSnakePoint {
        private int x;
        private int y;

        public RetroSnakePoint(
            int x,
            int y
        ) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object body) {
            if (!(body instanceof RetroSnakePoint)) {
                return false;
            }

            RetroSnakePoint obody = (RetroSnakePoint) body;
            return obody.x == this.x && obody.y == this.y;
        }
    }
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    public static class RetroSnake {
        private LinkedList<RetroSnakePoint> snakeBody;
        private Direction direction;

        public RetroSnake(LinkedList<RetroSnakePoint> snakeBody, Direction direction) {
            this.snakeBody = snakeBody;
            this.direction = direction;
        }

        public boolean turnUp() {
            if (this.direction != Direction.DOWN) {
                this.direction = Direction.UP;
                return true;
            }
            return false;
        }
        public boolean turnDown() {
            if (this.direction != Direction.UP) {
                this.direction = Direction.DOWN;
                return true;
            }
            return false;
        }
        public boolean turnLeft() {
            if (this.direction != Direction.RIGHT) {
                this.direction = Direction.LEFT;
                return true;
            }
            return false;
        }
        public boolean turnRight() {
            if (this.direction != Direction.LEFT) {
                this.direction = Direction.RIGHT;
                return true;
            }
            return false;
        }

        public RetroSnakePoint nextPoint() {
            RetroSnakePoint goal = null;
            RetroSnakePoint snakeHead = snakeBody.get(0);
            switch (direction) {
                case UP:
                    goal = new RetroSnakePoint(snakeHead.getX(), snakeHead.getY()-1);
                    break;
                case DOWN:
                    goal = new RetroSnakePoint(snakeHead.getX(), snakeHead.getY()+1);
                    break;
                case LEFT:
                    goal = new RetroSnakePoint(snakeHead.getX()-1, snakeHead.getY());
                    break;
                case RIGHT:
                    goal = new RetroSnakePoint(snakeHead.getX()+1, snakeHead.getY());
                    break;
                default:
                    break;
            }
            return goal;
        }

        public void eatFood(RetroSnakePoint point) {
            snakeBody.addFirst(point);
        }

        public void move(RetroSnakePoint point) {
            snakeBody.remove(snakeBody.size()-1);
            snakeBody.addFirst(point);
        }

        public boolean isOnBody(RetroSnakePoint point) {
            for (RetroSnakePoint snakePoint : snakeBody) {
                if (snakePoint.equals(point)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class SnakeGame extends JPanel implements KeyListener {
        private int[][] map;
        private int weight;
        private int height;
        private RetroSnake snake;
        private RetroSnakePoint food;
        private final Random random = new Random();
        private volatile boolean gameOver = false;
        private final List<RetroSnakePoint> candidatePoint;
        private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        private Future scheduledTaskFuture;

        public SnakeGame(
            int weight,
            int height
        ) {
            this.weight = weight;
            this.height = height;
            this.map = new int[height][weight];
            this.snake = new RetroSnake(new LinkedList<RetroSnakePoint>() {{
                add(new RetroSnakePoint(3, 1));
                add(new RetroSnakePoint(2, 1));
                add(new RetroSnakePoint(1, 1));
                add(new RetroSnakePoint(0, 1));
            }}, Direction.RIGHT);
            this.candidatePoint = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < weight; x++) {
                    if (map[y][x] == 0) {
                        RetroSnakePoint point = new RetroSnakePoint(x, y);
                        if (snake.isOnBody(point) == false) {
                            candidatePoint.add(point);
                        }
                    }
                }
            }
        }

        private boolean newFood() {
            int size = candidatePoint.size();
            if (size == 0) {
                return false;
            } else {
                int next = random.nextInt(10 * size) % size;
                food = candidatePoint.remove(next);
                return true;
            }
        }

        private synchronized boolean move() {
            if (gameOver == false) {
                RetroSnakePoint nextMove = snake.nextPoint();
                if (food.equals(nextMove)) {
                    snake.eatFood(nextMove);
                    if (newFood()) {
                        return true;
                    } else {
                        gameOver = true;
                        return false;
                    }
                } else {
                    if (nextMove.getX() >= weight || nextMove.getX() < 0) {
                        gameOver = true;
                        return false;
                    }
                    if (nextMove.getY() >= height || nextMove.getY() < 0) {
                        gameOver = true;
                        return false;
                    }
                    if (snake.isOnBody(nextMove)) {
                        gameOver = true;
                        return false;
                    }
                    if (map[nextMove.getY()][nextMove.getX()] != 0) {
                        gameOver = true;
                        return false;
                    }
                    snake.move(nextMove);
                    return true;
                }
            } else {
                return false;
            }
        }
        private synchronized boolean move(Direction direction) {
            boolean needRepaint = true;
            switch (direction) {
                case UP:
                    if (snake.turnUp()) {
                        move();
                    } else {
                        needRepaint = false;
                    }
                    break;
                case DOWN:
                    if (snake.turnDown()) {
                        move();
                    } else {
                        needRepaint = false;
                    }
                    break;
                case LEFT:
                    if (snake.turnLeft()) {
                        move();
                    } else {
                        needRepaint = false;
                    }
                    break;
                case RIGHT:
                    if (snake.turnRight()) {
                        move();
                    } else {
                        needRepaint = false;
                    }
                    break;
                default:
                    break;
            }
            return needRepaint;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            boolean needRepaint = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    needRepaint = move(Direction.DOWN);
                    break;
                case KeyEvent.VK_D:
                    needRepaint = move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_A:
                    needRepaint = move(Direction.LEFT);
                    break;
                case KeyEvent.VK_W:
                    needRepaint = move(Direction.UP);
                    break;
                default:
                    break;
            }

            if (gameOver) {
                JOptionPane.showMessageDialog(null, "GAME OVER");
            } else {
                if (needRepaint) {
                    repaint();
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            // 清除上一帧
            super.paint(g);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            List<RetroSnakePoint> snakeBody = snake.snakeBody;

            for (RetroSnakePoint point : snakeBody) {
                g.fillRect(point.getX()*10, point.getY()*10, 10, 10);
            }
            g.fillRect(food.getX()*10, food.getY()*10, 10, 10);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        public void start() {
            newFood();
            scheduledTaskFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    if (move()) {
                        repaint();
                    }
                }
            }, 500, 500, TimeUnit.MILLISECONDS);
        }
    }

    public static class SnakeWin extends JFrame {
        private int weight;
        private int height;
        private SnakeGame snakeGame;

        public SnakeWin(int weight, int height) {
            this.weight = weight;
            this.height = height;
            snakeGame = new SnakeGame(weight, height);
            addKeyListener(snakeGame);
            add(snakeGame);
        }

        public void init() {
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize((weight)*10, (height+2)*10);
            this.setTitle("Pan-Snake");
            // frame.setUndecorated(true);
            this.setVisible(true);
            this.setResizable(false);
            snakeGame.start();
            snakeGame.repaint();
        }
    }

    public static void main(String[] args) {
        SnakeWin snakeWin = new SnakeWin(23, 27);
        snakeWin.init();
    }

}
