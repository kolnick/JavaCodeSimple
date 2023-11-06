package com.caochaojie.behaviornode;


import com.caochaojie.exception.UnrealizedException;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public abstract  class AbstractActionNode extends AbstractBehaviorNode {

    @Override
    public AbstractBehaviorNode addChild(AbstractBehaviorNode childNode) {
        throw new UnrealizedException("行为节点没有子节点啦.... class=" + this.getClass().getName());
    }
}
