package uz.digitalone.appgmuzbekistan.res.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {

    private String name;
    private String downloadUrl;
    private String type;
    private long size;


}
