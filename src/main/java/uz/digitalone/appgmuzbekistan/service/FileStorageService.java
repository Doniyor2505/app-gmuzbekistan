package uz.digitalone.appgmuzbekistan.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String uploadFile(MultipartFile multipartFile);

    Resource downloadFile(String fileName);
}
