package com.polaris.he.framework.importer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * User: hexie
 * Date: 2019-01-16 23:25
 * Description:
 */
@Getter
@ToString
@AllArgsConstructor
public class UploadResult {

    private String mode;

    private String token;
}