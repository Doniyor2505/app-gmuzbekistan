package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.AddressCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Address;
import uz.digitalone.appgmuzbekistan.entity.District;
import uz.digitalone.appgmuzbekistan.repository.AddressRepository;
import uz.digitalone.appgmuzbekistan.repository.DistrictRepository;
import uz.digitalone.appgmuzbekistan.service.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final DistrictRepository districtRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              DistrictRepository districtRepository) {
        this.addressRepository = addressRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public Address save(AddressCreateDto dto) throws ClassNotFoundException {
        Optional<District> optionalDistrict = districtRepository.findById(dto.getDistrictId());
        if (optionalDistrict.isEmpty())
            throw new ClassNotFoundException("Such district id " + dto.getDistrictId() + " not found");
        District district = optionalDistrict.get();
        Address address = new Address(
                dto.getHome(),
                dto.getStreet(),
                district
        );
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    @Override
    public List<Address> findAll() {
        List<Address> findAll = addressRepository.findAll();
        return findAll;
    }

    @Override
    public Address findById(Long id) throws ClassNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id " + id + " not found");
        Address address = optionalAddress.get();
        return address;
    }

    @Override
    public Address edit(Long id, AddressCreateDto dto) throws ClassNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id " + id + " not found");
        Address address = optionalAddress.get();
        Optional<District> optionalDistrict = districtRepository.findById(dto.getDistrictId());
        if (optionalDistrict.isEmpty())
            throw new ClassNotFoundException("Such district id" + dto.getDistrictId() + " not found");
        District district = optionalDistrict.get();

        if(dto.getHome() != null && !dto.getHome().equals(address.getHome())){
            address.setHome(dto.getHome());
        }
        if(dto.getStreet() != null && !dto.getStreet().equals(address.getStreet())){
            address.setStreet(dto.getStreet());
        }
        if(dto.getDistrictId() != null && !dto.getDistrictId().equals(address.getDistrict().getId())){
            address.setDistrict(district);
        }
        Address editedAddress = addressRepository.save(address);
        return editedAddress;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id " + id + " not found");
        addressRepository.deleteById(id);
    }
}
