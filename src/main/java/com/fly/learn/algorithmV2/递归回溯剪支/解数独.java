package com.fly.learn.algorithmV2.递归回溯剪支;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 *  
 *
 * 示例：
 *
 *
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 *
 *  
 *
 * 提示：
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2021/5/19
 * @Description:
 */
public class 解数独 {

    /**
     * 数独首先行，列，还有 3*3 的方格内数字是 1~9 不能重复。
     *
     * 声明布尔数组，表明行列中某个数字是否被使用了， 被用过视为 true，没用过为 false。
     *
     * 初始化布尔数组，表明哪些数字已经被使用过了。
     *
     * 尝试去填充数组，只要行，列， 还有 3*3 的方格内 出现已经被使用过的数字，我们就不填充，否则尝试填充。
     *
     * 如果填充失败，那么我们需要回溯。将原来尝试填充的地方改回来。
     *
     * 递归直到数独被填充完成。
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // 行、列、3*3的方格
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        for(int row=0;row<board.length;row++){
            for(int col=0;col<board[0].length;col++){
                if(board[row][col] != '.'){
                    int num = board[row][col] - '0';
                    if(1 <= num && num <= 9){
                        rowUsed[row][num] = true;
                        colUsed[col][num] = true;
                        boxUsed[row/3][col/3][num] = true;
                    }
                }
            }
        }
        this.dfs(board,rowUsed,colUsed,boxUsed,0,0);
    }

    /**
     * 递归所有的空格
     * @param board
     * @param rowUsed
     * @param colUsed
     * @param boxUsed
     * @param row
     * @param col
     * @return
     */
    private boolean dfs(char[][] board,boolean[][] rowUsed,boolean[][] colUsed,boolean[][][] boxUsed,int row,int col){
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if(col == board[0].length){
            col = 0;
            row++;
            if(row == board.length){
                return true;
            }
        }
//        if(board[row][col] == '.'){
//            for(int num=1;num<=9;num++){
//                boolean notUse = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
//                if(notUse){
//                    rowUsed[row][num] = true;
//                    colUsed[col][num] = true;
//                    boxUsed[row/3][col/3][num] = true;
//                    board[row][col] = (char)('0' + num);;
//                    if(dfs(board,rowUsed,colUsed,boxUsed,row,col+1)){
//                        return true;
//                    }
//                    board[row][col] = '.';
//                    rowUsed[row][num] = false;
//                    colUsed[col][num] = false;
//                    boxUsed[row/3][col/3][num] = false;
//                }
//            }
//        }else{
//            dfs(board,rowUsed,colUsed,boxUsed,row,col+1);
//        }

        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if(board[row][col] == '.') {
            // 尝试填充1~9
            for(int num = 1; num <= 9; num++){
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
                if(canUsed){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;

                    board[row][col] = (char)('0' + num);
                    if(dfs(board, rowUsed, colUsed, boxUsed, row, col + 1)){
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row/3][col/3][num] = false;
                }
            }
        } else {
            return dfs(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }

}
