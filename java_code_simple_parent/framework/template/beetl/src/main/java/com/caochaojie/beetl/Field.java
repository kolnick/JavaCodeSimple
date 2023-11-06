package com.caochaojie.beetl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author caochaojie
 * 2023/1/10
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    private String name;

    private String comment;

    private String type;
}
