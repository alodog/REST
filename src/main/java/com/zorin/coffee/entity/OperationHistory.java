package com.zorin.coffee.entity;

import lombok.Data;
import javax.persistence.*;
import java.text.SimpleDateFormat;


/**
 * @author Zorin Sergey
 */

@Entity
@Table(name = "operations_history")
@Data
public class OperationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Operations operation;

    @Column(name = "operation_date")
    private String operationDate;

    public OperationHistory(Operations operation) {
        this.operation = operation;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.operationDate = sdf.format(System.currentTimeMillis());
    }

    public OperationHistory() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.operationDate = sdf.format(System.currentTimeMillis());
    }
}
