package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.AutoShopCreateDto;
import uz.digitalone.appgmuzbekistan.entity.AutoShop;
import uz.digitalone.appgmuzbekistan.entity.Car;

import java.util.List;

public interface AutoShopService {
    AutoShop save(AutoShopCreateDto dto) throws ClassNotFoundException;

    List<AutoShop> findAll();

    AutoShop findById(Long id) throws ClassNotFoundException;

    AutoShop edit(Long id, AutoShopCreateDto dto) throws ClassNotFoundException;

    List<AutoShop> findAllByIdCompanyId(Long id, String name);

    void delete(Long id);
}
