package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "right")
@Entity

public class Right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "right_id")
    private long rightId;
    @Column(name = "right_name")
    private String rightName;
    @Column(name = "description")
    private String description;

    public long getRightId() {
        return rightId;
    }

    public void setRightId(long rightId) {
        this.rightId = rightId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
