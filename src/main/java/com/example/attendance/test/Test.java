package com.example.attendance.test;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Test {
    @Id
    private int id;
    private String name;
    private int age;

}
