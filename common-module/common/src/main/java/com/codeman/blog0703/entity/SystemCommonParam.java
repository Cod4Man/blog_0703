package com.codeman.blog0703.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhanghongjie
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SystemCommonParam对象", description="")
public class SystemCommonParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codeType;

    private String codeKey;

    private String codeValue;

    private String desc;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean useabled;


}
