package com.arthurtokarev.bankmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lessons")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonBackReference
    private Building building;
}