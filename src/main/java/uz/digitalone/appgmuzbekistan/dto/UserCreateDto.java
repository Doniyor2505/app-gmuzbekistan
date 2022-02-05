package uz.digitalone.appgmuzbekistan.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    private String fullName;
    private String email;
    private Set<Long> roleIds;
}
