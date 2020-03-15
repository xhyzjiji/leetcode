package com.meituan.xhyzjiji.number.algorithmsFourthEdition;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Tetris {

    public static final List<int[][]> OBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        });
    }};
    public static final List<int[][]> JBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 1},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        });
    }};
    public static final List<int[][]> LBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 1, 0, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        });
    }};
    public static final List<int[][]> SBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 0, 1, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 1, 0}
        });
    }};
    public static final List<int[][]> ZBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 0}
        });
    }};
    public static final List<int[][]> IBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}
        });
    }};
    public static final List<int[][]> TBlockPoints = new ArrayList<int[][]>() {{
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0}
        });
        add(new int[][]{
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 0, 0}
        });
    }};

    public static abstract class Block {

        private final List<int[][]> blockStatePoints;
        private int blockStateNum;
        private int blockState;
        private int x;
        private int y;

        public int[][] getBlockPoint() {
            return blockStatePoints.get(blockState);
        }

        // 顺时针
        public int[][] turnCW() {
            this.blockState = (this.blockState + 1) % this.blockStateNum;
            return getBlockPoint();
        }

        // 逆时针
        public int[][] turnCCW() {
            this.blockState = (this.blockState - 1 + this.blockStateNum) % this.blockStateNum;
            return getBlockPoint();
        }

        public void moveUp() {
            this.y--;
        }

        public void moveDown() {
            this.y++;
        }

        public void moveLeft() {
            this.x--;
        }

        public void moveRight() {
            this.x++;
        }

        public int getBlockPos(
            int x,
            int y
        ) {
            return x * 4 + y;
        }

        public Block(
            List<int[][]> blockStatePoints,
            int x,
            int y
        ) {
            this.blockStatePoints = blockStatePoints;
            this.blockStateNum = blockStatePoints.size();
            this.blockState = 0;
            this.x = x;
            this.y = y;
        }

        public Block(
            List<int[][]> blockStatePoints,
            int blockState,
            int x,
            int y
        ) {
            this.blockStatePoints = blockStatePoints;
            this.blockStateNum = blockStatePoints.size();
            this.blockState = blockState % this.blockStateNum;
            this.x = x;
            this.y = y;
        }

        public int getBlockState() {
            return blockState;
        }

        public void setBlockState(int blockState) {
            this.blockState = blockState;
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
    }

    public static class OBlock extends Block {

        public OBlock(
            int x,
            int y
        ) {
            super(
                OBlockPoints,
                x,
                y
            );
        }

        public OBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                OBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class LBlock extends Block {

        public LBlock(
            int x,
            int y
        ) {
            super(
                LBlockPoints,
                x,
                y
            );
        }

        public LBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                LBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class JBlock extends Block {

        public JBlock(
            int x,
            int y
        ) {
            super(
                JBlockPoints,
                x,
                y
            );
        }

        public JBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                JBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class SBlock extends Block {

        public SBlock(
            int x,
            int y
        ) {
            super(
                SBlockPoints,
                x,
                y
            );
        }

        public SBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                SBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class ZBlock extends Block {

        public ZBlock(
            int x,
            int y
        ) {
            super(
                ZBlockPoints,
                x,
                y
            );
        }

        public ZBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                ZBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class IBlock extends Block {

        public IBlock(
            int x,
            int y
        ) {
            super(
                IBlockPoints,
                x,
                y
            );
        }

        public IBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                IBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    public static class TBlock extends Block {

        public TBlock(
            int x,
            int y
        ) {
            super(
                TBlockPoints,
                x,
                y
            );
        }

        public TBlock(
            int blockState,
            int x,
            int y
        ) {
            super(
                TBlockPoints,
                blockState,
                x,
                y
            );
        }
    }

    private static final int blockStyles = 7;

    public static class TetrisGame extends JPanel implements KeyListener {
        private volatile boolean gameOver = false;
        private final Random random = new Random();
        private int[][] map;
        private Block currentBlock;
        private int weight;
        private int height;
        private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        private Future scheduledTaskFuture;

        public TetrisGame(
            int weight,
            int height
        ) {
            this.weight = weight;
            this.height = height;
            this.map = new int[height][weight];
        }

        public boolean addNewBlock() {
            Block newBlock;
            int x = (weight - 4) / 2;
            int y = 0;
            switch (random.nextInt(blockStyles * 10 - 1) % blockStyles) {
                case 0:
                    newBlock = new OBlock(
                        x,
                        y
                    );
                    break;
                case 1:
                    newBlock = new LBlock(
                        x,
                        y
                    );
                    break;
                case 2:
                    newBlock = new JBlock(
                        x,
                        y
                    );
                    break;
                case 3:
                    newBlock = new SBlock(
                        x,
                        y
                    );
                    break;
                case 4:
                    newBlock = new ZBlock(
                        x,
                        y
                    );
                    break;
                case 5:
                    newBlock = new IBlock(
                        x,
                        y
                    );
                    break;
                case 6:
                    newBlock = new TBlock(
                        x,
                        y
                    );
                    break;
                default:
                    newBlock = new OBlock(
                        x,
                        y
                    );
                    break;
            }
            if (check(newBlock) == false) {
                // game over
                return false;
            } else {
                currentBlock = newBlock;
                return true;
            }
        }

        public boolean checkGameOver() {
            int y = 3;
            for (int x = 0; x < weight; x++) {
                if (map[y][x] == 1) {
                    return true;
                }
            }
            return false;
        }

        public boolean check(Block block) {
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

                        if (map[mapY][mapX] != 0) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public int delLine() {
            int count = 0;
            for (int y = 0; y < height; y++) {
                boolean needDel = true;
                for (int x = 0; x < weight; x++) {
                    if (map[y][x] == 0) {
                        needDel = false;
                        break;
                    }
                }

                // 消掉第y行
                if (needDel) {
                    for (int preY = y - 1; preY >= 0; preY--) {
                        map[preY + 1] = map[preY];
                    }
                    map[0] = new int[weight];
                    count++;
                }
            }

            return (2 >>> count);
        }

        public void addToMap() {
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
                        if (map[mapY][mapX] == 0) {
                            map[mapY][mapX] = blockPoints[yBias][xBias];
                        } else {
                            throw new RuntimeException("map conflict");
                        }
                    }
                }
            }
        }

        public void display() {
            int[][] mapLayer = new int[height][weight];
            for (int y = 0; y < height; y++) {
                System.arraycopy(
                    map[y],
                    0,
                    mapLayer[y],
                    0,
                    weight
                );
            }

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
                        if (mapLayer[mapY][mapX] == 0) {
                            mapLayer[mapY][mapX] = blockPoints[yBias][xBias];
                        } else {
                            throw new RuntimeException("map conflict");
                        }
                    }
                }
            }

            for (int y = 0; y < height; y++) {
                System.out.println(Arrays.toString(mapLayer[y]));
            }
        }

        public void start() {
            addNewBlock();
            scheduledTaskFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    if (moveDown()) {
                        repaint();
                    }
                }
            }, 500, 500, TimeUnit.MILLISECONDS);
        }

        public void restart() throws Exception {
            scheduledTaskFuture.cancel(true);
            gameOver = false;
            scheduledTaskFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    if (moveDown()) {
                        repaint();
                    }
                }
            }, 500, 500, TimeUnit.MILLISECONDS);
            map = new int[height][weight];
            start();
        }

        @Deprecated
        public void consolePlay() {
            start();
            display();

            Scanner scanner = new Scanner(System.in);
            String line = null;

            while (gameOver == false && (line = scanner.nextLine()) != null) {
                switch (line) {
                    case "a":
                        currentBlock.moveLeft();
                        if (check(currentBlock) == false) {
                            currentBlock.moveRight();
                        }
                        break;
                    case "s":
                        currentBlock.moveDown();
                        if (check(currentBlock) == false) {
                            currentBlock.moveUp();
                            addToMap();
                            delLine();
                            if (addNewBlock() == false) {
                                gameOver = true;
                            }
                        }
                        break;
                    case "d":
                        currentBlock.moveRight();
                        if (check(currentBlock) == false) {
                            currentBlock.moveLeft();
                        }
                        break;
                    case "j":
                        currentBlock.turnCW();
                        if (check(currentBlock) == false) {
                            currentBlock.turnCCW();
                        }
                        break;
                    case "k":
                        currentBlock.turnCCW();
                        if (check(currentBlock) == false) {
                            currentBlock.turnCW();
                        }
                        break;
                    case "w":
                    default:
                        break;
                }
                if (gameOver == false) {
                    display();
                }
            }
            System.out.println("game over");
        }

        private synchronized boolean moveDown() {
            currentBlock.moveDown();
            if (check(currentBlock) == false) {
                currentBlock.moveUp();
                addToMap();
                delLine();
                if (addNewBlock() == false) {
                    gameOver = true;
                    return false;
                }
            }
            return true;
        }

        private synchronized boolean moveLeft() {
            currentBlock.moveLeft();
            if (check(currentBlock) == false) {
                currentBlock.moveRight();
                return false;
            }
            return true;
        }

        private synchronized boolean moveRight() {
            currentBlock.moveRight();
            if (check(currentBlock) == false) {
                currentBlock.moveLeft();
                return false;
            }
            return true;
        }

        private synchronized boolean turnCW() {
            currentBlock.turnCW();
            if (check(currentBlock) == false) {
                currentBlock.turnCCW();
                return false;
            }
            return true;
        }

        private synchronized boolean turnCCW() {
            currentBlock.turnCCW();
            if (check(currentBlock) == false) {
                currentBlock.turnCW();
                return false;
            }
            return true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            boolean needRepaint = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    needRepaint = moveDown();
                    break;
                case KeyEvent.VK_D:
                    needRepaint = moveRight();
                    break;
                case KeyEvent.VK_A:
                    needRepaint = moveLeft();
                    break;
//                case KeyEvent.VK_J:
//                    needRepaint = turnCW();
//                    break;
//                case KeyEvent.VK_K:
//                    needRepaint = turnCCW();
//                    break;
                case KeyEvent.VK_W:
                    needRepaint = turnCW();
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
            //这一句很重要!!! 不加这句不会清楚以前的图像
            super.paint(g);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 画当前方块
            int[][] blockPoints = currentBlock.getBlockPoint();
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    if (blockPoints[y][x] == 1) {
                        g.fillRect((currentBlock.getX()+x) * 10, (currentBlock.getY()+y) * 10, 10, 10);
                    }
                }
            }
            // 画已经固定的方块
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < weight; x++) {
                    if (map[y][x] == 1) {
                        g.fillRect(x * 10, y * 10, 10, 10);
                    }
                }
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
        private TetrisGame tetrisGame;

        public TetrisWin(int weight, int height) {
            this.weight = weight;
            this.height = height;
            tetrisGame = new TetrisGame(weight, height);
            addKeyListener(tetrisGame);
            add(tetrisGame);
        }

        public void init() {
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize((weight)*10, (height+2)*10);
            this.setTitle("Pan-Tetris");
            // frame.setUndecorated(true);
            this.setVisible(true);
            this.setResizable(false);
            tetrisGame.start();
            tetrisGame.repaint();
        }
    }

    public static void main(String[] args) {
//        TetrisGame game = new TetrisGame(
//            6,
//            8
//        );
//        game.consolePlay();

        TetrisWin tetrisWin = new TetrisWin(13, 17);
        tetrisWin.init();
    }
}