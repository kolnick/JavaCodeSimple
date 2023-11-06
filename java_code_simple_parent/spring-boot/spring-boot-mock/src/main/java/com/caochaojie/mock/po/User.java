package com.caochaojie.mock.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author caochaojie
 * @Date 2022/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ToString
@Builder
public class User implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;

    private String name;

    private String sex;

    private long count;

}
