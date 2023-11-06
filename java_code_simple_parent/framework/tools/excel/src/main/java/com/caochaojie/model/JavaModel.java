package com.caochaojie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class JavaModel {
    private int columnNum;
    private String title;
    private String desc;
    private String type;
    private boolean empty;
}
