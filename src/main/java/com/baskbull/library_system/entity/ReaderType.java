package com.baskbull.library_system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("tb_reader_type")
public class ReaderType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 读者类别
     */
    @TableId(value = "rd_type", type = IdType.AUTO)
    private Integer rdType;

    /**
     * 读者类别名称
     */
    @TableField("rd_type_name")
    private String rdTypeName;

    /**
     * 可借书数量
     */
    @TableField("can_lend_qty")
    private Integer canLendQty;

    @TableField("can_lend_day")
    private Integer canLendDay;

    /**
     * 可续借的次数
     */
    @TableField("can_continue_times")
    private Integer canContinueTimes;

    /**
     * 罚款率
     */
    @TableField("punish_rate")
    private BigDecimal punishRate;

    /**
     * 证书有效期（年）
     */
    @TableField("date_valid")
    private Integer dateValid;
}
