package com.ldt.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskColumn {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer boardId;

}
