package com.lzj.admin.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PersistentLogins对象", description="")
public class PersistentLogins implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String series;

    private String token;

    private LocalDateTime lastUsed;


}
