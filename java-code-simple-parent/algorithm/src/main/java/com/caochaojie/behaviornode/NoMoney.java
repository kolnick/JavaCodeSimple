package com.caochaojie.behaviornode;

import cn.hutool.core.util.RandomUtil;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public class NoMoney extends AbstractConditionNode {
    @Override
    public NodeState update() {
        if (RandomUtil.randomBoolean()) {
            System.out.println("带钱了");
            return NodeState.FAILURE;
        }
        System.out.println("没带钱");
        return NodeState.SUCCESS;
    }

}
