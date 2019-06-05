package com.example.demo.review.goods;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.review.common.RedisUtil;
import com.example.demo.review.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service(GoodsService.SERVICE_NAME)
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RedisUtil<String,String> redisUtil;

    @Override
    public boolean save(Goods goods)  throws Exception{
        boolean flag = false;
        Goods goodsInfo = goodsDao.save(goods);
        //放入Redis
        String goodsName = redisUtil.get("goodsName");
        if("null".equals(goodsName) || StringUtils.isEmpty(goodsName)){
            redisUtil.set("goodsName","蒙脱石散");
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        goodsDao.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Goods goods) throws Exception {
        Goods goodsInfo = goodsDao.save(goods);
        return true;
    }

    @Override
    public List<Goods> finaAll() throws Exception {
        List<Goods> goodsList = goodsDao.findAll();
        return goodsList;
    }

    @Override
    public Goods finadByName(String goodsName) throws Exception {
        //使用JPA 默认查询方式查询
        Goods goodsInfo = goodsDao.findTopByGoodsName(goodsName);
        System.out.println(goodsInfo);

        //使用SQL 查询
        goodsInfo = goodsDao.getGoodsInfo(goodsName);
        System.out.println(goodsInfo);

        //使用HQL 查询
        goodsInfo = goodsDao.getGoods(goodsName);
        System.out.println(goodsInfo);

        //Redis
        String name = redisUtil.get("goodsName");
        System.out.println("name:"+name);

        return goodsInfo;
    }

    @Override
    public String getGoodsInfo() throws Exception {
        String sql = "select * from pub_goods";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        String info = JSON.toJSONString(maps);
        System.out.println(info);
        return info;
    }

}
