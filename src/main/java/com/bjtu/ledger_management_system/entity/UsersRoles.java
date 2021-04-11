package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "users_roles")
@Entity

public class UsersRoles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "u_id")
  private long id;
  @Column(name = "role_id")
  private long roleId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }
}
