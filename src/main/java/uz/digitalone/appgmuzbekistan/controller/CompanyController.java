package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.CompanyCreateDto;
import uz.digitalone.appgmuzbekistan.entity.GM;
import uz.digitalone.appgmuzbekistan.service.CompanyService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:31 PM
 */

@RestController
public class CompanyController {

    private final CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/api/companies", method = RequestMethod.POST)
    public GM save(@RequestBody CompanyCreateDto dto) throws ClassNotFoundException {
        GM company = companyService.save(dto);
        return company;
    }

    @RequestMapping(value = "/api/companies", method = RequestMethod.GET)
    public List<GM> findAll(){
        List<GM> gmList = companyService.findAll();
        return gmList;
    }

    @RequestMapping(value = "/api/companies/{id}", method = RequestMethod.GET)
    public GM findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        GM company = companyService.findById(id);
        return company;
    }

    @RequestMapping(value = "/api/companies/{id}", method = RequestMethod.PUT)
    public GM edit(@PathVariable("id") Long id, @RequestBody CompanyCreateDto dto) throws ClassNotFoundException {
        GM company = companyService.edit(id, dto);
        return company;
    }

    @RequestMapping(value = "/api/companies/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        companyService.delete(id);
        return "Successfully deleted";
    }
}
