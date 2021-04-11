package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "template")
@Entity
public class Template {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "temp_id")
  private long tempId;
  @Column(name = "creator_id")
  private long creatorId;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "d_id")
  private String departmentId;
  @Column(name = "temp_name")
  private String tempName;
  @Column(name = "description")
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
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
