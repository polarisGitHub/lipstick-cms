package com.polaris.he.lipstick.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-02-01 23:47
 * Description:
 */
@Getter
@Setter
@ToString
public class LipstickExtension {

    private String color;

    private String color1;

    private String colorNo;

    private String figure;

    private List<String> imgs;
}