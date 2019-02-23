package com.polaris.he.framework.importer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-16 23:11
 * Description:
 */
@Getter
@ToString
@AllArgsConstructor
public class UploadValidateErrorLine {

    private int line;

    private String error;
}