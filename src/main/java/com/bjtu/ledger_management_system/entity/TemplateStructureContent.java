package com.bjtu.ledger_management_system.entity;
import javax.persistence.*;

@Table(name = "template_structure_content")
@Entity
public class TemplateStructureContent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private long tempid;

  private long strucid;

  private String content;

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

  public long getStrucid() {
    return strucid;
  }

  public void setStrucid(long strucid) {
    this.strucid = strucid;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
