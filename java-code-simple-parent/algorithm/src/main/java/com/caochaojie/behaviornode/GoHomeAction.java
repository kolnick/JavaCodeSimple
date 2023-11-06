package com.caochaojie.behaviornode;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
@Slf4j
public class GoHomeAction extends AbstractActionNode {

    @Override
    public NodeState update() {
        log.info("走回家");
        return NodeState.SUCCESS;
    }

}
