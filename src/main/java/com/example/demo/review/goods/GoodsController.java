package com.example.demo.review.goods;

import com.example.demo.review.entity.Goods;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    final Log log = LogFactory.getLog(getClass());

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/save")
    @ResponseBody
    public Boolean save(@RequestBody Goods goods) throws Exception {
        goodsService.save(goods);
        return true;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Boolean update(@RequestBody  Goods goods) throws Exception{
        int id = goods.getId();
        if(id == 0){
            goodsService.save(goods);
        }else {
            goodsService.update(goods);
        }

        return true;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Boolean delete(Integer id) throws Exception{
        goodsService.delete(id);
        return true;
    }

    @RequestMapping("/goodsInfo")
    @ResponseBody
    public Goods getGoodsInfo(String goodsName) throws Exception {
        Goods goods = goodsService.finadByName(goodsName);
        String info = goodsService.getGoodsInfo();
        return goods;
    }
}
