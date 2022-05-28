package com.meituan.xhyzjiji.number.offer.leetcode;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        int rowNum = board.length;
        int colNum = board[0].length;
        boolean[][] visited = new boolean[rowNum][colNum];
        char[] wordChars = word.toCharArray();
        for (int r = 0; r < rowNum; ++r) {
            for (int c = 0; c < colNum; ++c) {
                if (board[r][c] == wordChars[0]) {
                    if (findWord(board, visited, r, c, wordChars, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, boolean[][] visited, int boardRow, int boardCol, char[] word, int wordPtr) {
        int rowNum = board.length;
        int colNum = board[0].length;

        if (board[boardRow][boardCol] == word[wordPtr]) {
            wordPtr++;
            if (wordPtr == word.length) {
                return true;
            } else {
                visited[boardRow][boardCol] = true;
                // up down left right
                if (boardRow > 0 && visited[boardRow-1][boardCol] == false) {
                    if (findWord(board, visited, boardRow-1, boardCol, word, wordPtr)) {
                        return true;
                    }
                }
                if (boardRow+1 < rowNum && visited[boardRow+1][boardCol] == false) {
                    if (findWord(board, visited, boardRow+1, boardCol, word, wordPtr)) {
                        return true;
                    }
                }
                if (boardCol > 0 && visited[boardRow][boardCol-1] == false) {
                    if (findWord(board, visited, boardRow, boardCol-1, word, wordPtr)) {
                        return true;
                    }
                }
                if (boardCol+1 < colNum && visited[boardRow][boardCol+1] == false) {
                    if (findWord(board, visited, boardRow, boardCol+1, word, wordPtr)) {
                        return true;
                    }
                }
                visited[boardRow][boardCol] = false;
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'},
        };
        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(board, "SEE"));
    }

}
