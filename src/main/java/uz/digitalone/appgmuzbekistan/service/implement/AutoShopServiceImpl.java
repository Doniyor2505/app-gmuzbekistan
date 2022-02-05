package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.AutoShopCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Address;
import uz.digitalone.appgmuzbekistan.entity.AutoShop;
import uz.digitalone.appgmuzbekistan.entity.Car;
import uz.digitalone.appgmuzbekistan.entity.GM;
import uz.digitalone.appgmuzbekistan.repository.AddressRepository;
import uz.digitalone.appgmuzbekistan.repository.AutoShopRepository;
import uz.digitalone.appgmuzbekistan.repository.CarRepository;
import uz.digitalone.appgmuzbekistan.repository.CompanyRepository;
import uz.digitalone.appgmuzbekistan.service.AutoShopService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AutoShopServiceImpl implements AutoShopService {

    private final AutoShopRepository autoShopRepository;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final CarRepository carRepository;

    public AutoShopServiceImpl(AutoShopRepository autoShopRepository,
                               CompanyRepository companyRepository,
                               AddressRepository addressRepository,
                               CarRepository carRepository) {
        this.autoShopRepository = autoShopRepository;
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.carRepository = carRepository;
    }

    @Override
    public AutoShop save(AutoShopCreateDto dto) throws ClassNotFoundException {
        Optional<GM> gmOptional = companyRepository.findById(dto.getCompanyId());
        if (gmOptional.isEmpty())
            throw new ClassNotFoundException("Such company id " + dto.getCompanyId() + " not found");
        GM company = gmOptional.get();
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id " + dto.getAddressId() + " not found");
        Address address = optionalAddress.get();

        Set<Car> carSet = new HashSet<>();
        for (Long carId : dto.getCarsId()) {
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isEmpty())
                throw new ClassNotFoundException("Such car id " + dto.getCarsId() + " not found");
            Car car = optionalCar.get();
            carSet.add(car);
        }
        AutoShop autoShop = new AutoShop(
                dto.getName(),
                address,
                company,
                carSet);
        AutoShop saved = autoShopRepository.save(autoShop);
        return saved;
    }

    @Override
    public List<AutoShop> findAll() {
        List<AutoShop> findAll = autoShopRepository.findAll();
        return findAll;
    }

    @Override
    public AutoShop findById(Long id) throws ClassNotFoundException {
        Optional<AutoShop> optionalAutoShop = autoShopRepository.findById(id);
        if (optionalAutoShop.isEmpty())
            throw new ClassNotFoundException("Such autoShop id" + id + " not found");
        AutoShop autoShop = optionalAutoShop.get();
        return autoShop;
    }

    @Override
    public AutoShop edit(Long id, AutoShopCreateDto dto) throws ClassNotFoundException {
        Optional<AutoShop> optionalAutoShop = autoShopRepository.findById(id);
        if (optionalAutoShop.isEmpty())
            throw new ClassNotFoundException("Such autoShop id" + id + "");
        AutoShop autoShop = optionalAutoShop.get();
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id" + dto.getAddressId() + " not found");
        Address address = optionalAddress.get();
        Optional<GM> optionalCompany = companyRepository.findById(dto.getCompanyId());
        if (optionalCompany.isEmpty())
            throw new ClassNotFoundException("Such company id " + dto.getCompanyId() + " not found");
        GM company = optionalCompany.get();

        Set<Car> carSet = new HashSet<>();
        for (Long carsId : dto.getCarsId()) {
            Optional<Car> optionalCar = carRepository.findById(carsId);
            if (optionalCar.isEmpty())
                throw new ClassNotFoundException("Such car id " + carsId + " not found");
            Car car = optionalCar.get();
            carSet.add(car);
        }
        if(dto.getName() != null && !dto.getName().equals(autoShop.getName())){
            autoShop.setName(dto.getName());
        }
        if(!dto.getAddressId().equals(autoShop.getAddress().getId())){
            autoShop.setAddress(address);
        }
        if(!dto.getCompanyId().equals(autoShop.getCompany().getId())){
            autoShop.setCompany(company);
        }
        if(!dto.getCarsId().equals(autoShop.getCars())){
            autoShop.setCars(carSet);
        }
        return autoShop;
    }

    @Override
    public void delete(Long id) {
        autoShopRepository.deleteById(id);
    }

    @Override
    public List<AutoShop> findAllByIdCompanyId(Long id, String name) {
        List<AutoShop> autoShopList = autoShopRepository.selectByCompanyIdNative(id, name);
        return autoShopList;
    }
}
