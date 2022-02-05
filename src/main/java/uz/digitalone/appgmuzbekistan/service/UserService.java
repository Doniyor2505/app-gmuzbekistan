package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.UserCreateDto;
import uz.digitalone.appgmuzbekistan.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserCreateDto dto) throws ClassNotFoundException;

    List<User> findAll();

    User findById(Long id) throws ClassNotFoundException;

    User edit(Long id, UserCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;

    List<User> findAllRoleId(Long id);

    User bindRole(Long userId, Set<Long> roleIds);
}
