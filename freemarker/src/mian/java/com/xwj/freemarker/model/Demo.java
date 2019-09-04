package com.xwj.freemarker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Demo implements Serializable {
    private int id;
    private String name;
    private double price;
    private LocalDate date;
}
