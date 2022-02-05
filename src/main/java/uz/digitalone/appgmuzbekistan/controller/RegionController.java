package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.RegionCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Region;
import uz.digitalone.appgmuzbekistan.service.RegionService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:29 PM
 */

@RestController
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @RequestMapping(value = "/api/regions", method = RequestMethod.POST)
    public Region save(@RequestBody RegionCreateDto dto){
        Region region = regionService.save(dto);
        return region;
    }

    @RequestMapping(value = "/api/regions", method = RequestMethod.GET)
    public List<Region> findAll(){

        List<Region> regionList = regionService.findAll();
        return regionList;
    }

    @RequestMapping(value = "/api/regions/{id}", method = RequestMethod.GET)
    public Region findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        Region region = regionService.findById(id);
        return region;
    }

    @RequestMapping(value = "/api/regions/{id}", method = RequestMethod.PUT)
    public Region edit(@PathVariable("id")Long id, @RequestBody RegionCreateDto dto) throws ClassNotFoundException {
        Region region = regionService.edit(id, dto);
        return region;
    }

    @RequestMapping(value = "/api/regions/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        regionService.delete(id);
        return "Successfully deleted";
    }




}
