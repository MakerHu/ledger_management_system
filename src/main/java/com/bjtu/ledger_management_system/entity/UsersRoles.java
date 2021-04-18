package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;

@Table(name = "users_roles")
@Entity

public class UsersRoles {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long uid;

  private long roleid;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getRoleid() {
    return roleid;
  }

  public void setRoleid(long roleid) {
    this.roleid = roleid;
  }
}
