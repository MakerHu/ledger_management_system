package com.bjtu.ledger_management_system.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "right")
@Entity

public class right {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long right_id;

    private String right_name;
    private String description;

    public long getRight_id() {
        return right_id;
    }

    public void setRight_id(long right_id) {
        this.right_id = right_id;
    }

    public String getRight_name() {
        return right_name;
    }

    public void setRight_name(String right_name) {
        this.right_name = right_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
