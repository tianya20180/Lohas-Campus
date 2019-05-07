package com.wx.happy.dao;

import com.wx.happy.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    Shop queryByShopId(long shopId);

    /**
     *
     *
     * @param shopCondition
     * @param rowIndex 从第几行开始取数据
     * @param pagaSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex")int rowIndex,

                             @Param("pageSize")int pagaSize);

    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
