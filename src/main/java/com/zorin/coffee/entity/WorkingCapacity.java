package com.zorin.coffee.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Zorin Sergey
 */
@Entity
@Table(name = "working_capacity")
@Data
public class WorkingCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "switched_on")
    private boolean SwitchedOn;

    public WorkingCapacity(boolean switchedOn) {
        this.id = 1;
        SwitchedOn = switchedOn;
    }

    public WorkingCapacity() {
    }
}
