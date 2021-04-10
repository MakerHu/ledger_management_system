package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "template_relation")
@Entity

public class TemplateRelation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long tempId;
  private String superId;
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
