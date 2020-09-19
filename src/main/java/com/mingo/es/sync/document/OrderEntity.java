package com.mingo.es.sync.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 单据ES实体
 *
 * @author Doflamingo
 */
@Data
@Document(indexName = "#{esConfig.name(T (com.mingo.es.sync.constant.type.EsIndexType).ORDER)}")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Keyword)
    private String tradeNo;

    @Field(type = FieldType.Long)
    private Long buyerId;

    @Field(type = FieldType.Long)
    private Long sellerId;

    @Field(type = FieldType.Integer)
    private Integer type;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Double)
    private BigDecimal amount;

    @Field(type = FieldType.Double)
    private BigDecimal discountAmount;

    @Field(type = FieldType.Double)
    private BigDecimal originAmount;

    @Field(type = FieldType.Double)
    private BigDecimal payAmount;

    @Field(type = FieldType.Date)
    private Date payTime;

    @Field(type = FieldType.Date)
    private Date deliveryTime;

    @Field(type = FieldType.Date)
    private Date receivingTime;

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

    @Field(type = FieldType.Nested)
    private List<OrderLineEntity> lines;
}