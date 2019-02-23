package com.polaris.he.framework.importer.converter;


import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 21:14
 * Description:
 */
public interface UploadByteConverter {

    /**
     * @param data
     * @param extension
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> convert(byte[] data, String extension, Class<T> clazz);
}