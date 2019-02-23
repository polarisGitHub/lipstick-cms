package com.polaris.he.framework.importer;

import com.polaris.he.framework.importer.data.UploadResult;

/**
 * User: hexie
 * Date: 2019-01-16 22:36
 * Description:
 */
public interface UploadImporter<T> {

    /**
     * @param data
     * @param listener
     * @return
     */
    UploadResult execute(byte[] data, String extension, UploadImportListener<T> listener);
}