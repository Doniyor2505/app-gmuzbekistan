package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.RoleCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Role;

import java.util.List;

public interface RoleService {
    Role save(RoleCreateDto dto);

    List<Role> findAll();

    Role findById(Long id) throws ClassNotFoundException;

    Role edit(Long id, RoleCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
