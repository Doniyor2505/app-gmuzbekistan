package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.CarCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Car;
import uz.digitalone.appgmuzbekistan.entity.GM;
import uz.digitalone.appgmuzbekistan.repository.CarRepository;
import uz.digitalone.appgmuzbekistan.repository.CompanyRepository;
import uz.digitalone.appgmuzbekistan.service.CarService;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CompanyRepository companyRepository;

    public CarServiceImpl(CarRepository carRepository,
                          CompanyRepository companyRepository) {
        this.carRepository = carRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Car save(CarCreateDto dto) throws ClassNotFoundException {
        Optional<GM> optionalGM = companyRepository.findById(dto.getCompanyId());
        if (optionalGM.isEmpty())
            throw new ClassNotFoundException("Such company id " + dto.getCompanyId() + " not found");
        GM company = optionalGM.get();
        Car car = new Car(
                dto.getName(),
                dto.getModel(),
                dto.getYear(),
                dto.getPrice(),
                company
        );
        Car save = carRepository.save(car);
        return save;
    }

    @Override
    public List<Car> findAll() {
        List<Car> findAll = carRepository.findAll();
        return findAll;
    }

    @Override
    public Car findById(Long id) throws ClassNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty())
            throw new ClassNotFoundException("Such car id " + id + " not found");
        Car car = optionalCar.get();
        return car;
    }

    @Override
    public Car edit(Long id, CarCreateDto dto) throws ClassNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty())
            throw new ClassNotFoundException("Such car id " + id + " not found");
        Car car = optionalCar.get();
        Optional<GM> gmOptional = companyRepository.findById(dto.getCompanyId());
        if (gmOptional.isEmpty())
            throw new ClassNotFoundException("Such company id " + dto.getCompanyId() + " not found");
        GM company = gmOptional.get();
        if (dto.getName() != null && !dto.getName().equals(car.getName())) {
            car.setName(dto.getName());
        }
        if (dto.getModel() != null && !dto.getModel().equals(car.getModel())) {
            car.setModel(dto.getModel());
        }
        if (dto.getYear() != null && !dto.getYear().equals(car.getYear())) {
            car.setYear(dto.getYear());
        }
        if (dto.getPrice() != null && !dto.getPrice().equals(car.getPrice())) {
            car.setYear(dto.getYear());
        }
        if (!dto.getCompanyId().equals(car.getCompany().getId())) {
            car.setCompany(company);
        }
        Car edited = carRepository.save(car);
        return edited;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty())
            throw new ClassNotFoundException("Such car id " + id + " not found");
        carRepository.deleteById(id);
    }
}
