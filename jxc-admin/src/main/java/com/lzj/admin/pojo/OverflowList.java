package com.lzj.admin.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报溢单表
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_overflow_list")
@ApiModel(value="OverflowList对象", description="报溢单表")
public class OverflowList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "报溢日期")
    private LocalDateTime overflowDate;

    @ApiModelProperty(value = "报溢单号")
    private String overflowNumber;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "操作用户id")
    private Integer userId;


}
