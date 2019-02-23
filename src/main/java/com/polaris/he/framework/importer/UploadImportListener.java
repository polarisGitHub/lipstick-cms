package com.polaris.he.framework.importer;

import com.polaris.he.framework.importer.data.UploadValidateResult;

import java.util.List;

/**
 * User: hexie
 * Date: 2019-01-16 22:38
 * Description:
 */
public interface UploadImportListener<T> {

    /**
     * @param data
     */
    void onBegin(byte[] data);

    /**
     * @param data
     * @param upload
     */
    void onBeforeValidate(byte[] data, List<T> upload);


    /**
     * @param upload
     * @param validateResult
     */
    void onAfterValidate(List<T> upload, UploadValidateResult validateResult);

    /**
     * @param upload
     */
    void onBeforeResolve(List<T> upload);

    /**
     * 是否异步模式
     *
     * @param upload
     * @return
     */
    boolean asyncOrNot(List<T> upload);

    /**
     * @param e
     */
    void onException(Exception e);

    /**
     *
     */
    void onEnd();
}