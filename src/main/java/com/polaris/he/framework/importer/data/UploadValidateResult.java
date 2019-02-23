package com.polaris.he.framework.importer.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 23:11
 * Description:
 */
@Getter
@ToString
@AllArgsConstructor
public class UploadValidateResult {

    private List<UploadValidateErrorLine> result;

    public boolean hasError() {
        return result != null && !result.isEmpty();
    }
}