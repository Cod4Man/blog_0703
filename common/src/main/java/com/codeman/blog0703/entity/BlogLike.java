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
@ApiModel(value="BlogLike对象", description="")
public class BlogLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "bl_id", type = IdType.AUTO)
    private Integer blId;

    private Integer blogId;

    private Integer userId;


}
