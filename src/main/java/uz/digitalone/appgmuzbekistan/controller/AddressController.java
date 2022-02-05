package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.AddressCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Address;
import uz.digitalone.appgmuzbekistan.service.AddressService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:29 PM
 */

@RestController
public class AddressController {

    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping(value = "/api/address", method = RequestMethod.POST)
    public Address save(@RequestBody AddressCreateDto dto) throws ClassNotFoundException {
        Address address = addressService.save(dto);
        return address;
    }

    @RequestMapping(value = "/api/address", method = RequestMethod.GET)
    public List<Address> findAll(){
        List<Address> addressList = addressService.findAll();
        return addressList;
    }

    @RequestMapping(value = "/api/address/{id}", method = RequestMethod.GET)
    public Address findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        Address address = addressService.findById(id);
        return address;
    }

    @RequestMapping(value = "/api/address/{id}", method = RequestMethod.PUT)
    public Address edit(@PathVariable("id") Long id, @RequestBody AddressCreateDto dto) throws ClassNotFoundException {
        Address editAddress = addressService.edit(id, dto);
        return editAddress;
    }

    @RequestMapping(value = "/api/address/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        addressService.delete(id);
        return "Successfully deleted";
    }

}
