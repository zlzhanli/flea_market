package com.flea.market.pojo;

import com.flea.market.util.Column;

import java.util.Objects;

/**
 * 首页轮播图实体
 *
 * @author SunTao
 * @Date 2019/3/16
 */
public class IndexPhoto {

    private Integer id;
    private String photoName;

    public IndexPhoto() {
    }

    public IndexPhoto(Integer id, String photoName) {
        this.id = id;
        this.photoName = photoName;
    }

    public Integer getId() {
        return id;
    }

    @Column(name = "id")
    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhotoName() {
        return photoName;
    }

    @Column(name = "photo_name")
    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexPhoto that = (IndexPhoto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(photoName, that.photoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoName);
    }

    @Override
    public String toString() {
        return "IndexPhoto{" +
                "id=" + id +
                ", photoName='" + photoName + '\'' +
                '}';
    }
}
