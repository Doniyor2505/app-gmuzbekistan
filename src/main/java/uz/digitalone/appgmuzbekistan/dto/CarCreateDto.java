package uz.digitalone.appgmuzbekistan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarCreateDto {

    private String name;
    private String model;
    private LocalDate year;
    private Double price;
    private Long companyId;

}
