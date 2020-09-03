package com.fly.learn.algorithm;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *  
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/9/3
 * @Description:
 */
public class N皇后问题 {

    // 记录某一列是否放置了皇后
    private Set<Integer> col;
    // 记录主对角线上的单元格是否放置了皇后
    private Set<Integer> main;
    // 记录了副对角线上的单元格是否放置了皇后
    private Set<Integer> sub;
    private int n;
    // 结果
    private List<List<String>> res = new ArrayList<>();

    /**
     * DFS解法
     * 遍历每行，然后验证列和对角线是否存在皇后
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        if(n ==0 ){
            return res;
        }
        // 设置成员变量，减少参数传递，具体作为方法参数还是作为成员变量，请参考团队开发规范
        this.n = n;
        this.col = new HashSet<>();
        this.main = new HashSet<>();
        this.sub = new HashSet<>();
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, path);
        return res;
    }

    private void dfs(int row, Deque<Integer> path) {
        if (row == n) {
            List<String> board = convert2board(path);
            res.add(board);
            return;
        }
        // 针对每一列，尝试是否可以放置
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !main.contains(row + i) && !sub.contains(row - i)) {
                path.addLast(i);
                col.add(i);
                main.add(row + i);
                sub.add(row - i);

                dfs(row + 1, path);

                sub.remove(row - i);
                main.remove(row + i);
                col.remove(i);
                path.removeLast();
            }
        }
    }

    private List<String> convert2board(Deque<Integer> path) {
        List<String> board = new ArrayList<>();
        for (Integer num : path) {
            StringBuilder row = new StringBuilder();
            for(int i=0;i<Math.max(0,n);i++){
                row.append(".");
            }
            row.replace(num, num + 1, "Q");
            board.add(row.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        N皇后问题 test = new N皇后问题();
        System.out.println(test.solveNQueens(4));
    }

}
