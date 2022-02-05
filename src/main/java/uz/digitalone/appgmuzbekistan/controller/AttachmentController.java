package uz.digitalone.appgmuzbekistan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uz.digitalone.appgmuzbekistan.res.response.UploadFileResponse;
import uz.digitalone.appgmuzbekistan.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final FileStorageService storageService;

    @PostMapping("/upload_file")
    public UploadFileResponse uploadFiles(@RequestParam("file") MultipartFile multipartFile){
        String fileName = storageService.uploadFile(multipartFile);

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        return new UploadFileResponse(fileName, downloadUrl, multipartFile.getContentType(), multipartFile.getSize());

    }
    @GetMapping("/downloadFile/{fileName: .+}")
    public ResponseEntity<Resource> downloadByFileName(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        Resource resource = storageService.downloadFile(fileName);
        String type = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(type)).body(resource);
    }

}
