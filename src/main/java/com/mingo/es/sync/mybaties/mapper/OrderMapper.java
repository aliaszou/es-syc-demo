package com.mingo.es.sync.mybaties.mapper;

import com.mingo.es.sync.mybaties.dataobjact.OrderDO;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Doflamingo
 */
public interface OrderMapper {

    String COLUMN = "trade_no,buyer_id,seller_id,type,status,amount,discount_amount," +
            "origin_amount,pay_amount,pay_time,delivery_time,receiving_time,create_time";

    String COL = "id, update_time, version, ext_data, deleted";

    /**
     * 插入一条数据
     *
     * @param orderDO
     */
    @Insert("INSERT INTO test_trade_order(" + COLUMN + ") VALUES" +
            "(#{tradeNo}, #{buyerId}, #{sellerId}, #{type}, #{status}, #{amount}," +
            "#{discountAmount}, #{originAmount}, #{payAmount}, #{payTime}, #{deliveryTime}, #{receivingTime}," +
            "#{createTime})")
    int insert(OrderDO orderDO);

    /**
     * 查询数据
     *
     * @param tradeNo
     * @return
     */
    @Select("SELECT " + COLUMN + "," + COL + " FROM test_trade_order WHERE `trade_no` = #{tradeNo}")
    OrderDO selectByTradeNo(@Param("tradeNo") String tradeNo);

    /**
     * 更新支付信息
     *
     * @param tradeNo
     * @param status
     * @return
     */
    @Update("UPDATE test_trade_order SET status = #{status},pay_amount = #{payAmount}, pay_time = #{payTime}, version = version + 1 " +
            "WHERE trade_no = #{tradeNo}")
    int updatePayStatus(@Param("tradeNo") String tradeNo, @Param("status") Integer status,
                        @Param("payAmount") BigDecimal payAmount, @Param("payTime") Date payTime);

    /**
     * 更新发货状态和时间
     *
     * @param tradeNo
     * @param status
     * @return
     */
    @Update("UPDATE test_trade_order SET status = #{status},delivery_time = #{deliveryTime}, version = version + 1 WHERE trade_no = #{tradeNo}")
    int updateDeliveryStatus(@Param("tradeNo") String tradeNo, @Param("status") Integer status, @Param("deliveryTime") Date deliveryTime);

    /**
     * 更新收货状态和时间
     *
     * @param tradeNo
     * @param status
     * @return
     */
    @Update("UPDATE test_trade_order SET status = #{status},receiving_time = #{receivingTime}, version = version + 1 WHERE trade_no = #{tradeNo}")
    int updateReceivingStatus(@Param("tradeNo") String tradeNo, @Param("status") Integer status, @Param("receivingTime") Date receivingTime);

    /**
     * 逻辑删除
     *
     * @param tradeNo
     * @return
     */
    @Update("UPDATE test_trade_order SET status = #{status}, version = version + 1 WHERE trade_no = #{tradeNo}")
    int updateStatus(@Param("tradeNo") String tradeNo, @Param("status") Integer status);

    /**
     * 物理删除
     *
     * @param tradeNo
     * @return
     */
    @Delete("DELETE FROM test_trade_order WHERE trade_no = #{tradeNo}")
    int delete(@Param("tradeNo") String tradeNo);
}
