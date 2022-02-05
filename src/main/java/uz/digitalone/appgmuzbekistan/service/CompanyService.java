package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.CompanyCreateDto;
import uz.digitalone.appgmuzbekistan.entity.GM;

import java.util.List;

public interface CompanyService {
    GM save(CompanyCreateDto dto) throws ClassNotFoundException;

    List<GM> findAll();

    GM findById(Long id) throws ClassNotFoundException;


    GM edit(Long id, CompanyCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
