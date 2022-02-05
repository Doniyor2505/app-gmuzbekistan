package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.DistrictCreateDto;
import uz.digitalone.appgmuzbekistan.entity.District;
import uz.digitalone.appgmuzbekistan.service.DistrictService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:29 PM
 */

@RestController
public class DistrictController {
    private final DistrictService districtService;
    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @RequestMapping(value = "/api/districts", method = RequestMethod.POST)
    public District save(@RequestBody DistrictCreateDto dto) throws ClassNotFoundException {
        District district = districtService.save(dto);
        return district;
    }

    @RequestMapping(value = "/api/districts", method = RequestMethod.GET)
    public List<District> findAll(){
        List<District> districtList = districtService.findAll();
        return districtList;
    }

    @RequestMapping(value = "/api/districts/{id}", method = RequestMethod.GET)
    public District findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        District district = districtService.findById(id);
        return district;
    }

    @RequestMapping(value = "/api/districts/{id}", method = RequestMethod.PUT)
    public District edit(@PathVariable("id") Long id, @RequestBody DistrictCreateDto dto) throws ClassNotFoundException {
        District editDistrict = districtService.edit(id, dto);
        return editDistrict;
    }

    @RequestMapping(value = "/api/districts/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        districtService.delete(id);
        return "Successfully deleted";
    }




}
