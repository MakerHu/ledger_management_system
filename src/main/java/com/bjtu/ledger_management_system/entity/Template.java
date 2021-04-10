package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "template")
@Entity
public class Template {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long tempId;
  private long creatorId;
  private java.sql.Timestamp createTime;
  private String dId;
  private String tempName;
  private String description;


  public long getTempId() {
    return tempId;
  }

  public void setTempId(long tempId) {
    this.tempId = tempId;
  }


  public long getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(long creatorId) {
    this.creatorId = creatorId;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public String getDId() {
    return dId;
  }

  public void setDId(String dId) {
    this.dId = dId;
  }


  public String getTempName() {
    return tempName;
  }

  public void setTempName(String tempName) {
    this.tempName = tempName;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
