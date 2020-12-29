package com.baskbull.library_system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("tb_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 图书序号
     */
    @TableId(value = "bk_id", type = IdType.AUTO)
    private Integer bkId;

    /**
     * 图书编号或条码号
     */
    @NotBlank(message = "图书编号不能为空")
    @TableField("bk_code")
    private String bkCode;

    /**
     * 书名
     */
    @NotBlank(message = "书名不能为空")
    @TableField("bk_name")
    private String bkName;

    /**
     * 作者
     */
    @TableField("bk_author")
    private String bkAuthor;

    /**
     * 出版社
     */
    @TableField("bk_press")
    private String bkPress;

    /**
     * 出版日期(年)
     */
    @JsonFormat(pattern = "yyyy",timezone = "GMT+8")
    @TableField("bk_date_press")
    private Year bkDatePress;

    /**
     * ISBN书号
     */
    @TableField("bk_isbn")
    private String bkIsbn;

    /**
     * 分类号（如：TP316-21/123)
     */
    @TableField("bk_catalog")
    private String bkCatalog;

    /**
     * 语言，0-中文，1-英文，2-日文，3-俄文，4-德文，5-法文，6-其他
     */
    @TableField("bk_language")
    private Integer bkLanguage;

    /**
     * 页数
     */
    @TableField("bk_pages")
    private Integer bkPages;

    /**
     * 价格
     */
    @TableField("bk_price")
    private BigDecimal bkPrice;

    /**
     * 入馆日期
     */
    @TableField("bk_date_in")
    private LocalDateTime bkDateIn;

    /**
     * 内容简介
     */
    @TableField("bk_brief")
    private String bkBrief;

    /**
     * 图书封面图片
     */
    @TableField("bk_cover")
    private String bkCover;

    /**
     * 图书状态，在馆，借出，遗失，变卖，销毁
     */
    @TableField("bk_status")
    private String bkStatus;


}
