package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "template")
@Entity
public class Template {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tempid;

  private Long creatorid;

  private Date createtime;

  private String did;

  private String tempname;

  private String description;

  public Long getTempid() {
    return tempid;
  }

  public void setTempid(Long tempid) {
    this.tempid = tempid;
  }

  public Long getCreatorid() {
    return creatorid;
  }

  public void setCreatorid(Long creatorid) {
    this.creatorid = creatorid;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getTempname() {
    return tempname;
  }

  public void setTempname(String tempname) {
    this.tempname = tempname;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
