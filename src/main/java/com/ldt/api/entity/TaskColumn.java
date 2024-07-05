package com.ldt.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TASKCOLUMNTABLE")
public class TaskColumn {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(insertable = false, updatable = false)
    private Integer boardId;
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    @JsonIgnore
    private Board board;

    @OneToMany(mappedBy = "taskColumn", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks;
}
