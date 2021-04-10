package com.bjtu.ledger_management_system.entity;
import javax.persistence.*;

@Table(name = "template_structure_content")
@Entity

public class TemplateStructureContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private String strucId;
  private long tempId;
  private String content;


  public String getStrucId() {
    return strucId;
  }

  public void setStrucId(String strucId) {
    this.strucId = strucId;
  }


  public long getTempId() {
    return tempId;
  }

  public void setTempId(long tempId) {
    this.tempId = tempId;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
