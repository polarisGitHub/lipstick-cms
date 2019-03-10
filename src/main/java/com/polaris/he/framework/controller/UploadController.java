package com.polaris.he.framework.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * User: hexie
 * Date: 2019-02-26 22:30
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class UploadController {

    @Value("${upload.path}")
    private String path;

    @PostMapping("/file/upload")
    public List<String> upload(MultipartFile[] files) throws IOException {
        List<String> ret = new LinkedList<>();
        for (MultipartFile file : files) {
            String uploadFileName = String.format("%s.%s",
                    UUID.randomUUID().toString().replace("-", ""),
                    FilenameUtils.getExtension(file.getOriginalFilename())
            );
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path + uploadFileName)))) {
                bos.write(file.getBytes());
                ret.add(uploadFileName);
            }
        }
        return ret;
    }
}