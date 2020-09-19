package com.mingo.es.sync.mybaties.provider;

import com.mingo.es.sync.mybaties.dataobjact.OrderLineDO;
import com.mingo.es.sync.mybaties.mapper.OrderLineMapper;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author Doflamingo
 */
public class OrderLineMapperProvider {

    /**
     * 拼接insert sql
     *
     * @param list
     * @return
     */
    public String insertSQL(List<OrderLineDO> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO test_trade_order_line ");
        sb.append("(" + OrderLineMapper.COLUMN + ") ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(" +
                "#'{'list[{0}].tradeNo},#'{'list[{0}].lineNo},#'{'list[{0}].itemCode},#'{'list[{0}].itemName}," +
                "#'{'list[{0}].unitCode},#'{'list[{0}].unitName},#'{'list[{0}].type},#'{'list[{0}].itemPrice}," +
                "#'{'list[{0}].price},#'{'list[{0}].discountPrice},#'{'list[{0}].itemQty},#'{'list[{0}].totalPrice}," +
                "#'{'list[{0}].paidPrice},#'{'list[{0}].createTime}" +
                ")");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
