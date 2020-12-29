package com.baskbull.library_system.entity;

import java.math.BigDecimal;
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
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("tb_borrow")
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "borrow_id", type = IdType.AUTO)
    private Long borrowId;

    @TableField("rd_id")
    private Integer rdId;

    @TableField("bk_id")
    private Integer bkId;

    /**
     * 续借次数
     */
    @TableField("id_continue_times")
    private Integer idContinueTimes;

    /**
     * 借书日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("id_date_out")
    private LocalDateTime idDateOut;

    /**
     * 应还日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("id_date_ret_plan")
    private LocalDateTime idDateRetPlan;

    /**
     * 实际还书日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("id_date_ret_act")
    private LocalDateTime idDateRetAct;

    /**
     * 超期天数
     */
    @TableField("id_over_day")
    private Long idOverDay;

    /**
     * 超期金额（应罚款金额）
     */
    @TableField("id_over_money")
    private BigDecimal idOverMoney;

    /**
     * 罚款金额
     */
    @TableField("id_punish_money")
    private BigDecimal idPunishMoney;

    /**
     * 是否还书，缺省为0-未还
     */
    @TableField("is_has_return")
    private String isHasReturn;

    /**
     * 借书操作员id
     */
    @TableField("operator_id")
    private Integer operatorId;

    /**
     * 借书操作员
     */
    @TableField("operator_lend")
    private String operatorLend;

    /**
     * 还书操作员
     */
    @TableField("operator_ret")
    private String operatorRet;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("create_time")
    private LocalDateTime createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField("update_time")
    private LocalDateTime updateTime;


}
