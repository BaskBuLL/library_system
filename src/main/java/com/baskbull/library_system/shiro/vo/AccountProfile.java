package com.baskbull.library_system.shiro.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * View Object 视图对象
 * @author baskbull
 */
@Data
public class AccountProfile implements Serializable {

    private Integer rdId;

    private String rdPwd;

    private String rdName;

}
