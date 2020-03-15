package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.Block;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.IBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.JBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.LBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.OBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.SBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.TBlock;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.TetrisGame;
import com.meituan.xhyzjiji.number.algorithmsFourthEdition.Tetris.ZBlock;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TetrisDual {

    public static class TetrisDualGame extends JPanel implements KeyListener {

        private int weight;
        private int height;
        private int[][][] map;
        private Block[] curBlock = new Block[2];
        private volatile boolean[] gameOver = new boolean[]{false, false};

        private final Random random = new Random();
        private final int blockStyles = 7;
        private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        private Future scheduledTaskFuture;

        public TetrisDualGame(
            int weight,
            int height
        ) {
            this.weight = weight;
            this.height = height;
            map = new int[2][][];
            map[0] = new int[height][weight];
            map[1] = new int[height][weight];
        }

        public Block newBlock() {
            Block newBlock;
            int x = (weight - 4) / 2;
            int y = 0;
            switch (random.nextInt(blockStyles * 10 - 1) % blockStyles) {
                case 0:
                    newBlock = new OBlock(x, y);
                    break;
                case 1:
                    newBlock = new LBlock(x, y);
                    break;
                case 2:
                    newBlock = new JBlock(x, y);
                    break;
                case 3:
                    newBlock = new SBlock(x, y);
                    break;
                case 4:
                    newBlock = new ZBlock(x, y);
                    break;
                case 5:
                    newBlock = new IBlock(x, y);
                    break;
                case 6:
                    newBlock = new TBlock(x, y);
                    break;
                default:
                    newBlock = new OBlock(x, y);
                    break;
            }
            return newBlock;
        }

        public boolean check(int player, Block block) {
            int[][] playerMap = map[player-1];
            int[][] blockPoints = block.getBlockPoint();
            for (int yBias = 0; yBias < 4; yBias++) {
                for (int xBias = 0; xBias < 4; xBias++) {
                    if (blockPoints[yBias][xBias] == 1) {
                        int mapX = block.getX() + xBias;
                        int mapY = block.getY() + yBias;
                        if (mapX < 0 || mapX >= weight) {
                            return false;
                        }
                        if (mapY < 0 || mapY >= height) {
                            return false;
                        }

                        if (playerMap[mapY][mapX] != 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public boolean addNewBlock(int player) {
            Block newBlock;
            newBlock = newBlock();
            if (check(player, newBlock)) {
                curBlock[player-1] = newBlock;
                return true;
            } else {
                gameOver(player);
                return false;
            }
        }

        public void gameOver(int player) {
            gameOver[player-1] = true;
        }

        public void addToMap(int player) {
            Block currentBlock = curBlock[player-1];
            int[][] curMap = map[player-1];

            Block block = currentBlock;
            int[][] blockPoints = block.getBlockPoint();
            int blockX = block.getX();
            int blockY = block.getY();
            for (int yBias = 0; yBias < 4; yBias++) {
                int mapY = blockY + yBias;
                if (mapY >= height) {
                    continue;
                }
                for (int xBias = 0; xBias < 4; xBias++) {
                    if (blockPoints[yBias][xBias] == 0) {
                        continue;
                    } else {
                        int mapX = blockX + xBias;
                        if (mapX >= weight) {
                            continue;
                        }
                        if (curMap[mapY][mapX] == 0) {
                            curMap[mapY][mapX] = blockPoints[yBias][xBias];
                        } else {
                            throw new RuntimeException("map conflict");
                        }
                    }
                }
            }
        }

        public int delLine(int player) {
            int[][] curMap = map[player-1];

            int count = 0;
            for (int y = 0; y < height; y++) {
                boolean needDel = true;
                for (int x = 0; x < weight; x++) {
                    if (curMap[y][x] == 0) {
                        needDel = false;
                        break;
                    }
                }

                // 消掉第y行
                if (needDel) {
                    for (int preY = y - 1; preY >= 0; preY--) {
                        curMap[preY + 1] = curMap[preY];
                    }
                    curMap[0] = new int[weight];
                    count++;
                }
            }

            return (2 >>> count);
        }

        private synchronized boolean moveDown(int player) {
            if (gameOver[player-1] == false) {
                Block currentBlock = curBlock[player - 1];
                currentBlock.moveDown();
                if (check(
                    player,
                    currentBlock
                ) == false) {
                    currentBlock.moveUp();
                    addToMap(player);
                    delLine(player);
                    if (addNewBlock(player) == false) {
                        gameOver(player);
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        private synchronized boolean moveLeft(int player) {
            if (gameOver[player-1] == false) {
                Block currentBlock = curBlock[player - 1];
                currentBlock.moveLeft();
                if (check(
                    player,
                    currentBlock
                ) == false) {
                    currentBlock.moveRight();
                    return false;
                }
                return true;
            }
            return false;
        }

        private synchronized boolean moveRight(int player) {
            if (gameOver[player-1] == false) {
                Block currentBlock = curBlock[player - 1];
                currentBlock.moveRight();
                if (check(
                    player,
                    currentBlock
                ) == false) {
                    currentBlock.moveLeft();
                    return false;
                }
                return true;
            }
            return false;
        }

        private synchronized boolean turnCW(int player) {
            if (gameOver[player-1] == false) {
                Block currentBlock = curBlock[player - 1];
                currentBlock.turnCW();
                if (check(
                    player,
                    currentBlock
                ) == false) {
                    currentBlock.turnCCW();
                    return false;
                }
                return true;
            }
            return false;
        }

        private synchronized boolean turnCCW(int player) {
            if (gameOver[player-1] == false) {
                Block currentBlock = curBlock[player - 1];
                currentBlock.turnCCW();
                if (check(
                    player,
                    currentBlock
                ) == false) {
                    currentBlock.turnCW();
                    return false;
                }
                return true;
            }
            return false;
        }

        public void init() {
            addNewBlock(1);
            addNewBlock(2);
        }

        public void start() {
            scheduledTaskFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    boolean p1 = moveDown(1);
                    boolean p2 = moveDown(2);
                    if (gameOver[0] && gameOver[1]) {

                    } else {
                        repaint();
                    }
                }
            }, 1000, 500, TimeUnit.MILLISECONDS);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            boolean needRepaint = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    needRepaint = moveDown(1);
                    break;
                case KeyEvent.VK_D:
                    needRepaint = moveRight(1);
                    break;
                case KeyEvent.VK_A:
                    needRepaint = moveLeft(1);
                    break;
                case KeyEvent.VK_W:
                    needRepaint = turnCW(1);
                    break;

                case KeyEvent.VK_DOWN:
                    needRepaint = moveDown(2);
                    break;
                case KeyEvent.VK_RIGHT:
                    needRepaint = moveRight(2);
                    break;
                case KeyEvent.VK_LEFT:
                    needRepaint = moveLeft(2);
                    break;
                case KeyEvent.VK_UP:
                    needRepaint = needRepaint = turnCW(2);
                    break;
                default:
                    break;
            }

            if (gameOver[0] && gameOver[1]) {
                JOptionPane.showMessageDialog(null, "GAME OVER");
            } else {
                if (needRepaint) {
                    repaint();
                }
            }
        }

        @Override
        public void paint(Graphics g) {
            //这一句很重要!!! 不加这句不会清楚以前的图像
            super.paint(g);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            int offsetX = 0;
            // player1
            // 画当前方块
            Block currentBlock = curBlock[0];
            int[][] blockPoints = currentBlock.getBlockPoint();
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (blockPoints[y][x] == 1) {
                        g.fillRect((currentBlock.getX()+x+offsetX) * 10, (currentBlock.getY()+y) * 10, 10, 10);
                    }
                }
            }
            // 画已经固定的方块
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < weight; x++) {
                    if (map[0][y][x] == 1) {
                        g.fillRect((x+offsetX) * 10, y * 10, 10, 10);
                    }
                }
            }

            // player2
            // 画当前方块
            offsetX = weight+1;
            currentBlock = curBlock[1];
            blockPoints = currentBlock.getBlockPoint();
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (blockPoints[y][x] == 1) {
                        g.fillRect((currentBlock.getX()+x+offsetX) * 10, (currentBlock.getY()+y) * 10, 10, 10);
                    }
                }
            }
            // 画已经固定的方块
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < weight; x++) {
                    if (map[1][y][x] == 1) {
                        g.fillRect((x+offsetX) * 10, y * 10, 10, 10);
                    }
                }
            }

            g.setColor(Color.RED);
            for (int y = 0; y < height; y++) {
                g.fillRect(weight*10, y*10, 10, 10);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }

    public static class TetrisWin extends JFrame {
        private int weight;
        private int height;
        private TetrisDualGame tetrisGame;

        public TetrisWin(int weight, int height) {
            this.weight = weight;
            this.height = height;
            tetrisGame = new TetrisDualGame(weight, height);
            addKeyListener(tetrisGame);
            add(tetrisGame);
        }

        public void init() {
            tetrisGame.init();
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize((weight*2+1)*10, (height+2)*10);
            this.setTitle("Pan-Tetris");
            // frame.setUndecorated(true);
            this.setVisible(true);
            this.setResizable(false);
            tetrisGame.start();
            tetrisGame.repaint();
        }
    }

    public static void main(String[] args) {
        TetrisWin tetrisWin = new TetrisWin(13, 17);
        tetrisWin.init();
    }

}
