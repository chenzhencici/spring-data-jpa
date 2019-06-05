package com.example.demo.review.goods;

import com.example.demo.review.entity.Goods;

import java.util.List;

public interface GoodsService {

    String SERVICE_NAME = "goods_service";

    boolean save(Goods goods) throws Exception;

    boolean delete(Integer id) throws Exception;

    boolean update(Goods goods) throws Exception;

    List<Goods> finaAll() throws Exception;

    Goods finadByName(String goodsName) throws Exception;

    String getGoodsInfo() throws Exception;

}
