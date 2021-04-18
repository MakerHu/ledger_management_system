package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "template_relation")
@Entity

public class TemplateRelation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long tempid;

  private long superid;

  private long subid;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTempid() {
    return tempid;
  }

  public void setTempid(long tempid) {
    this.tempid = tempid;
  }

  public long getSuperid() {
    return superid;
  }

  public void setSuperid(long superid) {
    this.superid = superid;
  }

  public long getSubid() {
    return subid;
  }

  public void setSubid(long subid) {
    this.subid = subid;
  }
}
