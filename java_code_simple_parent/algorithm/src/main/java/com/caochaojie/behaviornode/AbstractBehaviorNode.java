package com.caochaojie.behaviornode;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public abstract class AbstractBehaviorNode implements BehaviorNode {


    @Override
    public final NodeState tick() {
        // final 包装函数，防止打破调用契约
        return this.update();
    }

    /**
     * 行为树每次更新时调用.
     *
     * @return 返回节点行为结果
     */
    public abstract NodeState update();

    /**
     * 添加一个孩子节点.
     *
     * @param childNode 孩子节点
     * @return 返回这个孩子节点
     */
    public abstract AbstractBehaviorNode addChild(AbstractBehaviorNode childNode);
}
