package com.xwj.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Demo implements Serializable {
    private User user;
    private double price;
    private LocalDate date;
}
