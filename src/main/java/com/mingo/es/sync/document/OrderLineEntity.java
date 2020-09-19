package com.mingo.es.sync.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 单据明细es Entity
 */
@Data
public class OrderLineEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Keyword)
    private String tradeNo;

    @Field(type = FieldType.Text)
    private String lineNo;

    @Field(type = FieldType.Text)
    private String itemCode;

    @Field(type = FieldType.Text)
    private String itemName;

    @Field(type = FieldType.Text)
    private String unitCode;

    @Field(type = FieldType.Text)
    private String unitName;

    @Field(type = FieldType.Integer)
    private Integer type;

    @Field(type = FieldType.Double)
    private BigDecimal itemPrice;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal discountPrice;

    @Field(type = FieldType.Double)
    private BigDecimal itemQty;

    @Field(type = FieldType.Double)
    private BigDecimal totalPrice;

    @Field(type = FieldType.Double)
    private BigDecimal paidPrice;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Date)
    private Date updateTime;

    @Field(type = FieldType.Integer)
    private Integer version;

    @Field(type = FieldType.Text)
    private String extData;

    @Field(type = FieldType.Integer)
    private Integer deleted;
}