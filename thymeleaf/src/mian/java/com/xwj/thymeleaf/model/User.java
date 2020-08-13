package com.xwj.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
}
