package com.polaris.he.framework.importer.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polaris.he.application.utils.JsonUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 23:55
 * Description:
 */
public class BaseUploadByteConverter implements UploadByteConverter {

    @Override
    public <T> List<T> convert(byte[] data, String extension, Class<T> clazz) {
        UploadExtensionExtensionEnum uploadExtension = UploadExtensionExtensionEnum.valueOf(extension);
        switch (uploadExtension) {
            case json:
                return jsonConvert(data, clazz);
            case csv:
                return csvConvert(data, clazz);
            case xls:
                return xlsConvert(data, clazz);
            case xlsx:
                return xlsxConvert(data, clazz);
            default:
                throw new UnsupportedOperationException("不支持文件类型");
        }
    }

    private <T> List<T> jsonConvert(byte[] data, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = JsonUtils.getObjectMapper();
            return objectMapper.readValue(data, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            ExceptionUtils.wrapAndThrow(e);
        }
        return null;
    }

    private <T> List<T> csvConvert(byte[] data, Class<T> clazz) {
        throw new UnsupportedOperationException("不支持csv");
    }

    private <T> List<T> xlsConvert(byte[] data, Class<T> clazz) {
        throw new UnsupportedOperationException("不支持xls");
    }

    private <T> List<T> xlsxConvert(byte[] data, Class<T> clazz) {
        throw new UnsupportedOperationException("不支持xlsx");
    }


}