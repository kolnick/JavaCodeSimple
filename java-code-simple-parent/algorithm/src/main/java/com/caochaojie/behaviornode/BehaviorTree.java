package com.caochaojie.behaviornode;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public class BehaviorTree {

    private final BehaviorNode root;

    public BehaviorTree(BehaviorNode root) {
        this.root = root;
    }

    public void tick() {
        root.tick();
    }
}
