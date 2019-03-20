package com.flea.market.pojo;


import com.flea.market.util.Column;

public class City {

  private Integer id;
  private Integer pid;
  private String cityname;
  private Integer type;


  public Integer getId() {
    return id;
  }
  @Column(name="id")
  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getPid() {
    return pid;
  }
  @Column(name="pid")
  public void setPid(Integer pid) {
    this.pid = pid;
  }


  public String getCityname() {
    return cityname;
  }
  @Column(name="cityname")
  public void setCityname(String cityname) {
    this.cityname = cityname;
  }


  public Integer getType() {
    return type;
  }
  @Column(name="type")
  public void setType(Integer type) {
    this.type = type;
  }

}
