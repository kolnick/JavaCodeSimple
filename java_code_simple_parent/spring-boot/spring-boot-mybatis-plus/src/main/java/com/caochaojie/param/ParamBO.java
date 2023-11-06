package com.caochaojie.param;

import cn.hutool.db.Page;
import cn.hutool.db.sql.Order;
import lombok.Data;

/**
 * @author caochaojie
 * 2022/8/16
 * @description
 */
@Data
public class ParamBO extends Page {
    private Order order;
}
