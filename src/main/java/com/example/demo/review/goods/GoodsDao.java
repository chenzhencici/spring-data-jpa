package com.example.demo.review.goods;

import com.example.demo.review.common.BaseDao;
import com.example.demo.review.entity.Goods;
import org.springframework.data.jpa.repository.Query;

public interface GoodsDao extends BaseDao<Goods,Integer> {

    Goods findTopByGoodsName(String GoodsName);

    @Query(value = "select * from pub_goods where goods_name = ?1 ",nativeQuery = true)
    Goods getGoodsInfo(String goodsName);

    @Query(value = "from Goods where goodsName = ?1")
    Goods getGoods(String goodsName);
}
