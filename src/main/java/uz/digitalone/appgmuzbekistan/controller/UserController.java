package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.UserCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Role;
import uz.digitalone.appgmuzbekistan.entity.User;
import uz.digitalone.appgmuzbekistan.repository.RoleRepository;
import uz.digitalone.appgmuzbekistan.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:29 PM
 */

@RestController
public class UserController {

    private final UserService userService;
    public UserController(RoleRepository roleRepository, UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public User save(@RequestBody UserCreateDto dto) throws ClassNotFoundException {
        User user = userService.save(dto);
        return user;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public List<User> findAll(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT)
    public User edit(@PathVariable("id") Long id, @RequestBody UserCreateDto dto) throws ClassNotFoundException {
        User user = userService.edit(id, dto);
        return user;
    }

    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        userService.delete(id);
        return "Successfully deleted";
    }

    @RequestMapping(value = "/api/users/{id}/bind_role", method = RequestMethod.POST)
    public User bindRole(@PathVariable("id") Long userId,
                         @RequestParam("role_ids") Set<Long> roleIds){
        User user = userService.bindRole(userId,roleIds);
        return user;
    }


    @RequestMapping(value = "/api/users/{role_id}/roleId_users", method = RequestMethod.POST)
    public List<User> findAllRoleId(@PathVariable("role_id") Long id){
        List<User> userList = userService.findAllRoleId(id);
        return userList;
    }


}
