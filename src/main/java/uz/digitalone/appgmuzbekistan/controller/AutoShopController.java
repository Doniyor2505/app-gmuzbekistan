package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.AutoShopCreateDto;
import uz.digitalone.appgmuzbekistan.entity.AutoShop;
import uz.digitalone.appgmuzbekistan.entity.Car;
import uz.digitalone.appgmuzbekistan.service.AutoShopService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:31 PM
 */

@RestController
public class AutoShopController {

    private final AutoShopService autoShopService;
    public AutoShopController(AutoShopService autoShopService) {
        this.autoShopService = autoShopService;
    }

    @RequestMapping(value = "/api/auto_shops", method = RequestMethod.POST)
    public AutoShop save(@RequestBody AutoShopCreateDto dto) throws ClassNotFoundException {
        AutoShop save = autoShopService.save(dto);
        return save;
    }

    @RequestMapping(value = "/api/auto_shops", method = RequestMethod.GET)
    public List<AutoShop> findAll(){
        List<AutoShop> autoShopList = autoShopService.findAll();
        return autoShopList;
    }

    @RequestMapping(value = "/api/auto_shops/{id}", method = RequestMethod.GET)
    public AutoShop findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        AutoShop autoShop = autoShopService.findById(id);
        return autoShop;
    }

    @RequestMapping(value = "/api/auto_shops/{id}", method = RequestMethod.PUT)
    public AutoShop edit(@PathVariable("id") Long id, @RequestBody AutoShopCreateDto dto) throws ClassNotFoundException {
        AutoShop edit = autoShopService.edit(id, dto);
        return edit;
    }

    @RequestMapping(value = "/api/auto_shops/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        autoShopService.delete(id);
        return "Successfully deleted";
    }

    @RequestMapping(value = "/api/auto_shops/{company_id}", method = RequestMethod.POST)
    public List<AutoShop> findAllByCompanyId(@PathVariable("company_id") Long id, @RequestParam(value = "company_name") String name){
        List<AutoShop> autoShopList = autoShopService.findAllByIdCompanyId(id,name);
        return autoShopList;
    }

}
