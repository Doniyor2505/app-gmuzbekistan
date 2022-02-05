package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.RoleCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Role;
import uz.digitalone.appgmuzbekistan.service.RoleService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:32 PM
 */

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/api/roles", method = RequestMethod.POST)
    public Role save(@RequestBody RoleCreateDto dto){
        Role role = roleService.save(dto);
        return role;
    }

    @RequestMapping(value = "/api/roles", method = RequestMethod.GET)
    public List<Role> findAll(){
        List<Role> roleList = roleService.findAll();
        return roleList;
    }

    @RequestMapping(value = "/api/roles/{id}", method = RequestMethod.GET)
    public Role findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        Role role = roleService.findById(id);
        return role;
    }

    @RequestMapping(value = "/api/roles/{id}", method = RequestMethod.PUT)
    public Role edit(@PathVariable("id") Long id, @RequestBody RoleCreateDto dto) throws ClassNotFoundException {
        Role editRole = roleService.edit(id, dto);
        return editRole;
    }

    @RequestMapping(value = "/api/roles/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        roleService.delete(id);
        return "Successfully deleted";
    }
}
