package com.teaching.point.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Integer total;
}
