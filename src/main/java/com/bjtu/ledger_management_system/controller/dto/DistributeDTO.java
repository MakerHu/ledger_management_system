package com.bjtu.ledger_management_system.controller.dto;

import java.util.List;

/**
 * 角色分配DTO
 */
public class DistributeDTO {
  private Long uid;
  private List<Long> roleidList;

  public DistributeDTO() {
  }

  public DistributeDTO(Long uid, List<Long> roleidList) {
    this.uid = uid;
    this.roleidList = roleidList;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public List<Long> getRoleidList() {
    return roleidList;
  }

  public void setRoleidList(List<Long> roleidList) {
    this.roleidList = roleidList;
  }

  @Override
  public String toString() {
    return "DistributeDTO{" +
        "uid=" + uid +
        ", roleidList=" + roleidList +
        '}';
  }
}
