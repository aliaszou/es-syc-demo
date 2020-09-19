package com.mingo.es.sync.mybaties.dataobjact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Doflamingo
 */
@Data
@Accessors(chain = true)
@ApiModel("单据ES实体")
public class OrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("交易订单号")
    private String tradeNo;

    @ApiModelProperty("买家id")
    private Long buyerId;

    @ApiModelProperty("买家id")
    private Long sellerId;

    @ApiModelProperty("交易类型 1-官方商城 2-传统零售")
    private Integer type;

    @ApiModelProperty("交易状态 1-待支付 3-待发货 5-待收货 7-已收货 9-完成 0-已取消")
    private Integer status;

    @ApiModelProperty("实际金额")
    private BigDecimal amount;

    @ApiModelProperty("折扣金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("原始金额")
    private BigDecimal originAmount;

    @ApiModelProperty("支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    @ApiModelProperty("收货时间")
    private Date receivingTime;

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

    @ApiModelProperty("明细信息")
    private List<OrderLineDO> lines;
}