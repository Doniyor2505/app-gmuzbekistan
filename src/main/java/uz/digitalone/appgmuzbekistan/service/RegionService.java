package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.RegionCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Region;

import java.util.List;

public interface RegionService {
    Region save(RegionCreateDto dto);

    List<Region> findAll();

    Region findById(Long id) throws ClassNotFoundException;

    Region edit(Long id, RegionCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
