package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.AddressCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Address;

import java.util.List;

public interface AddressService {
    Address save(AddressCreateDto dto) throws ClassNotFoundException;

    List<Address> findAll();

    Address findById(Long id) throws ClassNotFoundException;

    Address edit(Long id, AddressCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
