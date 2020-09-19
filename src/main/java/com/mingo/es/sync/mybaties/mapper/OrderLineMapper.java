package com.mingo.es.sync.mybaties.mapper;

import com.mingo.es.sync.mybaties.dataobjact.OrderLineDO;
import com.mingo.es.sync.mybaties.provider.OrderLineMapperProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Doflamingo
 */
public interface OrderLineMapper {

    String COLUMN = "trade_no,line_no,item_code,item_name,unit_code,unit_name,type," +
            "item_price,price,discount_price,item_qty,total_price,paid_price,create_time";

    String COL = "id, update_time, version, ext_data, deleted";

    /**
     * 批量插入
     *
     * @param list
     */
    @InsertProvider(type = OrderLineMapperProvider.class, method = "insertSQL")
    void batchSave(@Param("list") List<OrderLineDO> list);

    /**
     * 查询
     *
     * @param tradeNo
     * @return
     */
    @Select("SELECT " + COLUMN + "," + COL + " FROM test_trade_order_line WHERE `trade_no` = #{tradeNo}")
    List<OrderLineDO> select(@Param("tradeNo") String tradeNo);

    /**
     * 物理删除
     *
     * @param tradeNo
     * @return
     */
    @Delete("DELETE FROM test_trade_order_line WHERE trade_no = #{tradeNo}")
    int delete(@Param("tradeNo") String tradeNo);
}
