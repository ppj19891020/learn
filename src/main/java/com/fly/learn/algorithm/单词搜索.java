package com.fly.learn.algorithm;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/13
 * @Description:
 */
public class 单词搜索 {

    private int row; // 二维数组board的行数
    private int col; // board的列数
    private int len; // 字符串word的长度
    private char[][] board; // board：二维数组
    private char[] chs; // 字符串word转换成字符数组
    private int[][] visited; // 二维数组访问标记，未访问为0，已访问为1

    public boolean exist(char[][] board, String word) {
        this.board = board;
        row = board.length;
        col = board[0].length;
        len = word.length();
        visited = new int[row][col];
        chs = word.toCharArray();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 从某个位置开始搜索，若匹配，返回true
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false; // 从所有位置开始搜索都不匹配
    }

    private boolean dfs(int i, int j, int start) {
        // 不相等时，返回false
        if (board[i][j] != chs[start]) {
            return false;
        }
        // 下面的情况都蕴含着board[i][j] == chs[start]
        if (start == len - 1) {
            return true;
        }
        visited[i][j] = 1; // 做标记，已访问board[i][j]
        // 上下左右四种情况
        if ((i - 1 >= 0) && visited[i-1][j] == 0 && dfs(i-1, j, start + 1)) {
            return true;
        }
        if ((i + 1 < row) && visited[i+1][j] == 0 && dfs(i+1, j, start + 1)) {
            return true;
        }
        if ((j - 1 >= 0) && visited[i][j-1] == 0 && dfs(i, j-1, start + 1)) {
            return true;
        }
        if ((j + 1 < col) && visited[i][j+1] == 0 && dfs(i, j+1, start + 1)) {
            return true;
        }
        visited[i][j] = 0; // 回溯，从新标记board[i][j]还未访问
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        单词搜索 test = new 单词搜索();
        test.exist(board,"ABCCED");
    }

}
