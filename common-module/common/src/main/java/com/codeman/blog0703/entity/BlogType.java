package com.codeman.blog0703.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="BlogType对象", description="")
public class BlogType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Integer typeId;

    private Integer blogId;

    private String typeCodeKey;


}
