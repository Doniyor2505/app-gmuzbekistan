package uz.digitalone.appgmuzbekistan.service;

import uz.digitalone.appgmuzbekistan.dto.CarCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Car;

import java.util.List;

public interface CarService {
    Car save(CarCreateDto dto) throws ClassNotFoundException;

    List<Car> findAll();

    Car findById(Long id) throws ClassNotFoundException;

    Car edit(Long id, CarCreateDto dto) throws ClassNotFoundException;

    void delete(Long id) throws ClassNotFoundException;
}
