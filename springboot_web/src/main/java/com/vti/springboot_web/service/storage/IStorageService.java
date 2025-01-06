package com.vti.springboot_web.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    void store(MultipartFile file);
    void init();
}
