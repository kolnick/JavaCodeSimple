package com.caochaojie.secrity.modules.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p> 修改时间 </p>
 *
 * @description:
 * @author: zhengqing
 * @date: 2019/8/18 0018 1:30
 */
@Getter
@Setter
public abstract class BaseEntity<T extends Model> extends BaseAddEntity {
    /**
     * 修改时间 - 过去分词表示被动更新
     */
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
//    @Future(message = "修改时间必须是将来时间")
    private Date gmtModified;
    /**
     * 修改人
     */
//    @TableField(value = "modifier_id", fill = FieldFill.INSERT_UPDATE)
//    private Long modifierId;
}
