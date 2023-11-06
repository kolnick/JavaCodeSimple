package com.caochaojie.shardingjdbc.entity;

import lombok.Data;

/**
 * @author caochaojie
 * @date 2021/9/18
 */
@Data
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
