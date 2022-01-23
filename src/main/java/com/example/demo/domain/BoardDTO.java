package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private Integer id;
    private String title;
    private String tool;
    private Integer period;
    private String sub;
}
