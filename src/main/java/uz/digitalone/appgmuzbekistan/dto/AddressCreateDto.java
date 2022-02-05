package uz.digitalone.appgmuzbekistan.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateDto {

    private String home;
    private String street;
    private Long districtId;

}
