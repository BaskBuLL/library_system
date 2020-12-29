package com.baskbull.library_system.common.lang.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author baskbull
 */
@Data
public class BorrowDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "借书证号不能为空")
    private Integer rdId;

    @NotNull(message = "图书序号不能为空")
    private Integer bkId;

    @NotNull(message = "操作者id不能为空")
    private Integer operatorId;
}
