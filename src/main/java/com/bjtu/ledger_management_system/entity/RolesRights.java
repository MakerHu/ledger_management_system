package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "roles_rights")
@Entity
public class RolesRights {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long roleId;
  private long rightId;


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public long getRightId() {
    return rightId;
  }

  public void setRightId(long rightId) {
    this.rightId = rightId;
  }

}
