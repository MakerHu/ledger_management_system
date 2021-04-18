package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "roles_rights")
@Entity
public class RolesRights {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long roleid;

  private Long rightid;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getRoleid() {
    return roleid;
  }

  public void setRoleid(Long roleid) {
    this.roleid = roleid;
  }

  public Long getRightid() {
    return rightid;
  }

  public void setRightid(Long rightid) {
    this.rightid = rightid;
  }
}
