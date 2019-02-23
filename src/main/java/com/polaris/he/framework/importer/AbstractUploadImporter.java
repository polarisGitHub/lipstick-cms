package com.polaris.he.framework.importer;

import com.polaris.he.framework.importer.converter.UploadExtensionExtensionEnum;
import com.polaris.he.framework.importer.data.UploadResult;
import com.polaris.he.framework.importer.data.UploadValidateResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User: hexie
 * Date: 2019-01-16 22:35
 * Description:
 */
public abstract class AbstractUploadImporter<T> implements UploadImporter<T> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    abstract public List<T> byteConvert(byte[] data, String extension);

    abstract public UploadValidateResult validate(List<T> data);

    @Transactional
    abstract public void resolved(List<T> data);

    @Override
    @SuppressWarnings("unchecked")
    public UploadResult execute(byte[] data, String extension, UploadImportListener<T> listener) {
        logger.info("导入开始");
        Assert.notNull(UploadExtensionExtensionEnum.valueOf(StringUtils.lowerCase(extension)), "不支持上传类型");

        Optional<UploadImportListener> optionalListener = Optional.ofNullable(listener);
        try {
            optionalListener.ifPresent(l -> l.onBegin(data));
            List<T> upload = byteConvert(data, extension);
            logger.info("导入格式转换完成");

            optionalListener.ifPresent(l -> l.onBeforeValidate(data, upload));
            UploadValidateResult validateResult = validate(upload);
            optionalListener.ifPresent(l -> l.onAfterValidate(upload, validateResult));
            if (validateResult.hasError()) {
                logger.info("导入校验错误：{}", validateResult);
                return new UploadResult(null, UUID.randomUUID().toString());
            }
            logger.info("导入校验完成");

            // 判断是否异步上传，暂不支持
            boolean isAsync = optionalListener.map(l -> l.asyncOrNot(upload)).orElse(false);

            optionalListener.ifPresent(l -> l.onBeforeResolve(upload));
            ((AbstractUploadImporter) AopContext.currentProxy()).resolved(upload);
            logger.info("导入流程完");

            return new UploadResult(isAsync ? "async" : "sync", UUID.randomUUID().toString());
        } catch (Exception e) {
            logger.error("导入失败", e);
            optionalListener.ifPresent(l -> l.onException(e));
            throw e;
        } finally {
            optionalListener.ifPresent(UploadImportListener::onEnd);
        }
    }
}