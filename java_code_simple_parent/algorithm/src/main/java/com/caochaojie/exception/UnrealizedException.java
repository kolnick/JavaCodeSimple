package com.caochaojie.exception;

/**
 * @author caochaojie
 * 2022/12/13
 * @description
 */
public class UnrealizedException extends RuntimeException {
    private static final long serialVersionUID = 8947098802476902429L;

    public UnrealizedException(String msg) {
        super(msg);
    }

}