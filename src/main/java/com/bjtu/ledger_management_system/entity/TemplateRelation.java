package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "template_relation")
@Entity

public class TemplateRelation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "temp_id")
  private long tempId;
  @Column(name = "super_id")
  private String superId;
  @Column(name = "sub_id")
  private String subId;

  public long getTempId() {
    return tempId;
  }

  public void setTempId(long tempId) {
    this.tempId = tempId;
  }

  public String getSuperId() {
    return superId;
  }

  public void setSuperId(String superId) {
    this.superId = superId;
  }

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }
}
