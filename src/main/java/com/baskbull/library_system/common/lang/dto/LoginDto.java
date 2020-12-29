package com.baskbull.library_system.common.lang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author baskbull
 */
@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "借书证号不能为空")
    private Integer rdId;

    @NotBlank(message = "密码不能为空")
    private String rdPwd;
}
