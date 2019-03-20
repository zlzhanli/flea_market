package com.flea.market.pojo;

import com.flea.market.util.Column;

/**
 * @author: zhh
 * @time: 2019/3/12 9:22
 */

public class GoodsImg {

    private Integer id;
    private String imgName;
    private Integer goodsId;

    public Integer getId() {
        return id;
    }

    /**
     * 附属图片主键id
     * @param id
     */
    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    /**
     * 附属图名称
     * @param imgName
     */
    @Column(name = "uuidname")
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * 商品id
     * @param goodsId
     */
    @Column(name = "goodsid")
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}
