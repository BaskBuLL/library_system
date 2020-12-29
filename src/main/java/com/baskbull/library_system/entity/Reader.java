package com.baskbull.library_system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author baskbull
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_reader")
public class Reader implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 读者编号/借书证号
     */
    @NotNull
    @TableField(value = "rd_id")
    private Integer rdId;

    /**
     * 读者姓名
     */
    @TableField("rd_name")
    private String rdName;

    /**
     * 性别
     */
    @TableField("rd_sex")
    private String rdSex;

    @TableField("rd_type")
    private Integer rdType;

    /**
     * 电话号码
     */
    @TableField("rd_phone")
    private String rdPhone;

    /**
     * 单位代码/单位名称
     */
    @TableField("rd_dept")
    private String rdDept;

    /**
     * 邮箱
     */
    @TableField("rd_email")
    private String rdEmail;

    /**
     * 读者登记日期/办证日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("rd_date_reg")
    private LocalDateTime rdDateReg;

    /**
     * 读者照片
     */
    @TableField("rd_photo")
    private String rdPhoto;

    /**
     * 证件状态，3个：有效、挂失、注销
     */
    @TableField("rd_status")
    private Integer rdStatus;

    /**
     * 已借书数量
     */
    @TableField("rd_borrow_qty")
    private Integer rdBorrowQty;

    /**
     * 读者密码
     */
    @TableField("rd_pwd")
    private String rdPwd;

    /**
     * 管理角色
     */
    @TableField("rd_admin_roles")
    private Integer rdAdminRoles;


}
