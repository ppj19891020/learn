package com.fly.learn.designpattern.strategy;

/**
 * @author: peijiepang
 * @date 2018/11/5
 * @Description:
 */
public class Context {

    private Stractegy stractegy;

    public Context(Stractegy stractegy){
        this.stractegy = stractegy;
    }

    public void algorithm(){
        this.stractegy.algorithmInterface();
    }

    public static void main(String[] args) {
        Context context = new Context(new StractegyA());
        context.algorithm();
    }
}
