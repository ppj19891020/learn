package com.fly.learn.seda;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: peijiepang
 * @date 2020/7/24
 * @Description:
 */
public class StageManager {

    private Map<Event,Stage> stages = new HashMap<>();

    /**
     * 获取stage
     * @param event
     * @return
     */
    public Stage getStage(Event event){
        return stages.get(event);
    }

    /**
     * 初始化引导
     */
    public void boot(){

    }

}
