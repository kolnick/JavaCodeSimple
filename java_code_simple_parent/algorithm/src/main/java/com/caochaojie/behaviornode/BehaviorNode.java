package com.caochaojie.behaviornode;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public interface BehaviorNode {

    /**
     * 令行为树从根节点开始执行
     *
     * @return 返回行为节点的执行结果
     */
    NodeState tick();

}
