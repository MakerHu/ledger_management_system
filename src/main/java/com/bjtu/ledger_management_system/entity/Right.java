package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "rights")
@Entity

public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rightid;

    private String rightname;

    private String description;

    public Long getRightid() {
        return rightid;
    }

    public void setRightid(Long rightid) {
        this.rightid = rightid;
    }

    public String getRightname() {
        return rightname;
    }

    public void setRightname(String rightname) {
        this.rightname = rightname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
