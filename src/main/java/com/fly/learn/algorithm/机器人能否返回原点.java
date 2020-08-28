package com.fly.learn.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 *
 * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 *
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "UD"
 * 输出: true
 * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我们返回 true。
 * 示例 2:
 *
 * 输入: "LL"
 * 输出: false
 * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动结束时没有返回原点。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: peijiepang
 * @date 2020/8/28
 * @Description:
 */
public class 机器人能否返回原点 {

    /**
     * 解法1
     * x y当做坐标，直接按照每个命令移动
     * @param moves
     * @return
     */
    public boolean judgeCircle1(String moves) {
        int x = 0;
        int y = 0;
        char[] chars = moves.toCharArray();
        for(int i=0;i<chars.length;i++){
            char t = chars[i];
            if(t == 'R'){
                x++;
            }else if(t == 'L'){
                x--;
            }else if(t == 'U'){
                y++;
            }else if(t == 'D'){
                y--;
            }else{
                throw new RuntimeException("不支持命令:"+t);
            }
        }
        return x==0&&y==0;
    }

    /**
     * 解法2
     * 统计每个字符的数量，比较 RL和UD是否相等
     * @param moves
     * @return
     */
    public boolean judgeCircle2(String moves) {
        Map<Character,Integer> countMap = new HashMap<>();
        countMap.put('R',0);
        countMap.put('L',0);
        countMap.put('U',0);
        countMap.put('D',0);

        char[] chars = moves.toCharArray();
        for(char t:chars){
            countMap.put(t,countMap.get(t)+1);
        }
        if(countMap.get('R').intValue() == countMap.get('L').intValue() && countMap.get('U').intValue()==countMap.get('D').intValue()){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        机器人能否返回原点 test = new 机器人能否返回原点();
        System.out.println(test.judgeCircle2("UD"));
        System.out.println(test.judgeCircle2("RULDDLLDLRDUUUURULRURRRRLRULRLULLLRRULULDDRDLRULDRRULLUDDURDLRRUDRUDDURLLLUUDULRUDRLURRDRLLDDLLLDLRLLRUUDUURDRLDUDRUDRLUDULRLUDRLDDUULDDLDURULUDUUDDRRDUURRLRDLDLRLLDRRUUURDLULLURRRRDRRURDUURDLRRUULRURRUULULUUDURUDLRDDDDDURRRLRUDRUULUUUULDURDRULLRRRUDDDUUULUURRDRDDRLLDRLDULDLUUDRDLULLDLDDRUUUUDDRRRDLLLLURUURLRUUULRDDULUULUURDURDDDRRURLURDLLLRLULRDLDDLRDRRRRLUURRRRLDUDLLRUDLDRDLDRUULDRDULRULRRDLDLLLUDLDLULLDURUURRLLULUURLRLRDUDULLDURRUDDLDDLLUDURLLRLDLDUDLURLLDRRURRDUDLDUULDUDRRUDULLUUDURRRURLULDDLRRURULUURURRDULUULDDDUUDRLDDRLULDUDDLLLDLDURDLRLUURDDRLUDRLUDLRRLUUULLDUUDUDURRUULLDDUDLURRDDLURLDRDRUDRLDDLDULDRULUDRRDRLLUURULURRRUDRLLUURULURRLUULRDDDRDDLDRLDRLDUDRLDRLDDLDUDDURUDUDDDLRRDLUUUDUDURLRDRURUDUDDRDRRLUDURULDULDDRLDLUURUULUDRLRLRLLLLRLDRURRRUULRDURDRRDDURULLRDUDRLULRRLLLDRLRLRRDULDDUDUURLRULUUUULURULDLDRDRLDDLRLURRUULRRLDULLUULUDUDRLDUDRDLLDULURLUDDUURULDURRUURLRDRRRLDDULLLLDDRRLRRDRDLRUDUUDLRLDRDRURULDLULRRDLLURDLLDLRDRURLRUDURDRRRULURDRURLDRRRDUDUDUDURUUUUULURDUDDRRDULRDDLULRDRULDRUURRURLUDDDDLDRLDLLLLRLDRLRDRRRLLDRDRUULURLDRULLDRRDUUDLURLLDULDUUDLRRRDDUDRLDULRDLLULRRUURRRURLRRLDDUDDLULRUDULDULRDUDRLRDULRUUDDRUURUDLDRDUDDUULLUDDLLRLURURLRRULLDDDLURDRRDLLLLULLDLUDDLURLLDDRLDLLDDRDRDDUDLDURLUUUUUDLLLRLDULDDRDDDDRUDLULDRRLLLDUUUDDDRDDLLULUULRRULRUDRURDDULURDRRURUULDDDDUULLLURRRRDLDDLRLDDDRLUUDRDDRDDLUDLUUULLDLRDLURRRLRDRLURUURLULLLLRDDLLLLRUDURRLDURULURULDDRULUDRLDRLLURURRRDURURDRRUDLDDLLRRDRDDLRLRLUDUDRRUDLLDUURUURRDUDLRRLRURUDURDLRRULLDLLUDURUDDRUDULLDUDRRDDUDLLLDLRDRUURLLDLDRDDLDLLUDRDDRUUUDDULRUULRDRUDUURRRURUDLURLRDDLUULRDULRDURLLRDDDRRUDDUDUDLLDDRRUUDURDLLUURDLRULULDULRUURUDRULDRDULLULRRDDLDRDLLLDULRRDDLDRDLLRDDRLUUULUURULRULRUDULRULRUURUDUUDLDUDUUURLLURDDDUDUDLRLULDLDUDUULULLRDUDLDRUDRUULRURDDLDDRDULRLRLRRRRLRULDLLLDDRLUDLULLUUDLDRRLUDULRDRLLRRRULRLRLLUDRUUDUDDLRLDRDDDDRDLDRURULULRUURLRDLLDDRLLRUDRRDDRDUDULRUDULURRUDRDLRDUUDDLDRUDLLDDLRLULLLRUUDRRRRUULLRLLULURLDUDDURLRULULDLDRURDRLLURRDLURRURLULDLRLDUDLULLLDRDLULDLRULLLUDUDUDUDLDDDDDRDLUDUULLUDRLUURDRLULD"));
    }

}
