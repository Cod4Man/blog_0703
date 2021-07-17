package com.codeman.blog0703.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2021/7/3 16:27
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "com.codeman.blog0703.vo.CommonOutVO", description = "统一响应对象")
public class CommonOutVO {

    @ApiModelProperty(value = "错误码")
    private int code;
    @ApiModelProperty(value = "响应数据")
    private String data;
    @ApiModelProperty(value = "错误信息")
    private String error;
}
