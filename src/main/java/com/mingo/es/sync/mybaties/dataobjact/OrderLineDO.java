package com.mingo.es.sync.mybaties.dataobjact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Doflamingo
 */
@Data
@ApiModel("单据明细es DO")
public class OrderLineDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("交易订单号")
    private String tradeNo;

    @ApiModelProperty("行号")
    private String lineNo;

    @ApiModelProperty("商品编码")
    private String itemCode;

    @ApiModelProperty("商品名称")
    private String itemName;

    @ApiModelProperty("单位编码")
    private String unitCode;

    @ApiModelProperty("单位名称")
    private String unitName;

    @ApiModelProperty("商品类型 1-普通")
    private Integer type;

    @ApiModelProperty("原价")
    private BigDecimal itemPrice;

    @ApiModelProperty("实际金额")
    private BigDecimal price;

    @ApiModelProperty("折扣金额")
    private BigDecimal discountPrice;

    @ApiModelProperty("商品数量")
    private BigDecimal itemQty;

    @ApiModelProperty("总原始金额")
    private BigDecimal totalPrice;

    @ApiModelProperty("实际支付总金额")
    private BigDecimal paidPrice;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("版本")
    private Integer version;

    @ApiModelProperty("扩展数据")
    private String extData;

    @ApiModelProperty("是否删除 1-正常 2-删除")
    private Integer deleted;
}