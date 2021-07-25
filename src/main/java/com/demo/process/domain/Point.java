package com.demo.process.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Point {

    private int poiId;

    private int memId;

    private LocalDate poiDtm;

    private String poiContent;

    private int poiPoint;

    private String poiType;

    private String poiAction;

}
