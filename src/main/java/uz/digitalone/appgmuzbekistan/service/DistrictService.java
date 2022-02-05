package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.DistrictCreateDto;
import uz.digitalone.appgmuzbekistan.entity.District;

import java.util.List;

public interface DistrictService {
    District save(DistrictCreateDto dto) throws ClassNotFoundException;

    List<District> findAll();

    District findById(Long id) throws ClassNotFoundException;

    District edit(Long id, DistrictCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
