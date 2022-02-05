package uz.digitalone.appgmuzbekistan.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictCreateDto {

    private String name;
    private Double area;
    private Long population;
    private Long regionId;
}
