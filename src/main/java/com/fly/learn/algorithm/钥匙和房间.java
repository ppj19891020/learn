package com.fly.learn.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * 示例 1：
 *
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 示例 2：
 *
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * 提示：
 *
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * 所有房间中的钥匙数量总计不超过 3000。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keys-and-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/8/31
 * @Description:
 */
public class 钥匙和房间 {

    private boolean[] vis;
    private int nums;


    /**
     * 广度优先搜索
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        boolean[] vis = new boolean[rooms.size()];
        Queue<Integer> queue = new ArrayBlockingQueue<Integer>(rooms.size());
        vis[0] = true;
        queue.offer(0);
        while (!queue.isEmpty()){
            int x = queue.poll();
            nums++;
            for(int y:rooms.get(x)){
                if(!vis[y]){
                    vis[y] = true;
                    queue.offer(y);
                }
            }
        }
        return nums == rooms.size();
    }


    /**
     * 深度优先
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        // 已访问过的房间
        vis = new boolean[rooms.size()];
        bsf(rooms,0);
        return nums == rooms.size();
    }

    public void bsf(List<List<Integer>> rooms,int x){
        vis[x] = true;
        nums++;
        for(int it:rooms.get(x)){
            if(!vis[it]){
                bsf(rooms,it);
            }
        }
    }

    public static void main(String[] args) {
        钥匙和房间 test = new 钥匙和房间();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,3));
        list.add(Arrays.asList(3,0,1));
        list.add(Arrays.asList(2));
        list.add(Arrays.asList(0));
        test.canVisitAllRooms1(list);
//        test.canVisitAllRooms2(list);
    }

}
