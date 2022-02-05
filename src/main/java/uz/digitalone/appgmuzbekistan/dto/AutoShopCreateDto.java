package uz.digitalone.appgmuzbekistan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoShopCreateDto {

    private String name;
    private Long addressId;
    private Long companyId;
    private Set<Long> carsId;
}
