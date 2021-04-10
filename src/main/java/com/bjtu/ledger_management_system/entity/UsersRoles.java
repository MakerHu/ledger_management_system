package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "users_roles")
@Entity

public class UsersRoles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long uId;
  private long roleId;


  public long getUId() {
    return uId;
  }

  public void setUId(long uId) {
    this.uId = uId;
  }


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


}
