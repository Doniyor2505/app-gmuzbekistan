package uz.digitalone.appgmuzbekistan.controller;

import org.springframework.web.bind.annotation.*;
import uz.digitalone.appgmuzbekistan.dto.CarCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Car;
import uz.digitalone.appgmuzbekistan.service.CarService;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:29 PM
 */

@RestController
public class CarController {

    private final CarService carService;
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/api/cars", method = RequestMethod.POST)
    public Car save(@RequestBody CarCreateDto dto) throws ClassNotFoundException {
        Car save = carService.save(dto);
        return save;
    }

    @RequestMapping(value = "/api/cars", method = RequestMethod.GET)
    public List<Car> findAll(){
        List<Car> carList = carService.findAll();
        return carList;
    }

    @RequestMapping(value = "/api/cars/{id}", method = RequestMethod.GET)
    public Car findById(@PathVariable("id") Long id) throws ClassNotFoundException {
        Car car = carService.findById(id);
        return car;
    }

    @RequestMapping(value = "/api/cars/{id}", method = RequestMethod.PUT)
    public Car edit(@PathVariable("id") Long id, @RequestBody CarCreateDto dto) throws ClassNotFoundException {
        Car edited = carService.edit(id, dto);
        return edited;
    }

    @RequestMapping(value = "/api/cars/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id) throws ClassNotFoundException {
        carService.delete(id);
        return "Successfully deleted";
    }
}
