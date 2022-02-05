package uz.digitalone.appgmuzbekistan.service.implement;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.digitalone.appgmuzbekistan.exception.FileStorageException;
import uz.digitalone.appgmuzbekistan.properties.StorageProperties;
import uz.digitalone.appgmuzbekistan.service.FileStorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileLocation;

    public FileStorageServiceImpl(StorageProperties storageProperties) {
        this.fileLocation = Paths.get(storageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public String uploadFile(MultipartFile multipartFile) {

        String filename = multipartFile.getOriginalFilename();
        if(filename.contains(".."))
            throw new FileStorageException("File nomida xatolik boldi! " + filename);
        Path path = this.fileLocation.resolve(filename);
        Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    @SneakyThrows
    @Override
    public Resource downloadFile(String fileName) {
        Path path = this.fileLocation.resolve(fileName);
        Resource resource = new UrlResource(path.toUri());
        if(resource.exists()){
            return resource;
        }
        throw new ClassNotFoundException("File Not Found");

    }
}
