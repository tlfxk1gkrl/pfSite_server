package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorksDTO {
    private Integer id;
    private String title;
    private String kind;
    private String sub;
    private String imgLink;
    private Integer imgCnt;
    private String gitSub;
    private String gitLink;
    private String titleJap;
    private String subJap;
}
