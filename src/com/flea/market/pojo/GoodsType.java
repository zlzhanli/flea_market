package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 商品类型实体类
 *
 * @author SunTao
 * @Date 2019/3/6
 */
public class GoodsType {

    private Integer typeId;
    private String typeName;
    private Integer typePid;
    private String typePath;
    private Integer typeLv;


    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 商品类型的id
     *
     * @param typeId 商品类型id
     */
    @Column(name = "type_id")
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }


    public String getTypeName() {
        return typeName;
    }

    /**
     * 商品类型名称
     *
     * @param typeName 商品类型名称
     */
    @Column(name = "type_name")
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public Integer getTypePid() {
        return typePid;
    }

    /**
     * 该商品类型的父类型id
     *
     * @param typePid 父类型id
     */
    @Column(name = "type_pid")
    public void setTypePid(Integer typePid) {
        this.typePid = typePid;
    }


    public String getTypePath() {
        return typePath;
    }

    /**
     * 该商品类型在类型所属关系中的路径
     *
     * @param typePath 类型路径
     */
    @Column(name = "type_path")
    public void setTypePath(String typePath) {
        this.typePath = typePath;
    }


    public Integer getTypeLv() {
        return typeLv;
    }

    /**
     * 该商品类型在类型所属关系中的级别
     *
     * @param typeLv 类型级别
     */
    @Column(name = "type_lv")
    public void setTypeLv(Integer typeLv) {
        this.typeLv = typeLv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsType goodsType = (GoodsType) o;
        return typeId.equals(goodsType.typeId) &&
                typePid.equals(goodsType.typePid) &&
                typeLv.equals(goodsType.typeLv) &&
                Objects.equals(typeName, goodsType.typeName) &&
                Objects.equals(typePath, goodsType.typePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeName, typePid, typePath, typeLv);
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typePid=" + typePid +
                ", typePath='" + typePath + '\'' +
                ", typeLv=" + typeLv +
                '}';
    }
}
