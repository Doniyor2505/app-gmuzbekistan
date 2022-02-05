package uz.digitalone.appgmuzbekistan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateDto {

    private String name;
    private Long addressId;
    private Long directorId;
}
