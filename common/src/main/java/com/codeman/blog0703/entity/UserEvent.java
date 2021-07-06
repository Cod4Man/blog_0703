package com.codeman.blog0703.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@ApiModel(value="UserEvent对象", description="")
public class UserEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "event_id", type = IdType.AUTO)
    private Integer eventId;

    private Integer userId;

    private Integer userIp;

    private String userAction;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
