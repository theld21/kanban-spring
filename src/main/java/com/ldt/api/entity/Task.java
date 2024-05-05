package com.ldt.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @Column(length = 2000)
    private String description;
    @Column(insertable = false, updatable = false)
    private Integer taskColumnId;

    @ManyToOne
    @JoinColumn(name = "taskColumnId")
    @JsonIgnore
    private TaskColumn taskColumn;

}
