package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.CompanyCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Address;
import uz.digitalone.appgmuzbekistan.entity.GM;
import uz.digitalone.appgmuzbekistan.entity.User;
import uz.digitalone.appgmuzbekistan.repository.AddressRepository;
import uz.digitalone.appgmuzbekistan.repository.CompanyRepository;
import uz.digitalone.appgmuzbekistan.repository.UserRepository;
import uz.digitalone.appgmuzbekistan.service.CompanyService;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AddressRepository addressRepository,
                              UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GM save(CompanyCreateDto dto) throws ClassNotFoundException {
        Optional<Address> byId = addressRepository.findById(dto.getAddressId());
        if (byId.isEmpty())
            throw new ClassNotFoundException("Such address id " + dto.getAddressId() + " not found");
        Address address = byId.get();

        Optional<User> optionalUser = userRepository.findById(dto.getDirectorId());
        if (optionalUser.isEmpty())
            throw new ClassNotFoundException("Such director id" + dto.getDirectorId() + " not found");
        User user = optionalUser.get();

        GM company = new GM(
                dto.getName(),
                address,
                user
        );
        GM saved = companyRepository.save(company);
        return saved;
    }

    @Override
    public List<GM> findAll() {
        List<GM> findAllCompany = companyRepository.findAll();
        return findAllCompany;
    }

    @Override
    public GM findById(Long id) throws ClassNotFoundException {
        Optional<GM> optionalGM = companyRepository.findById(id);
        if (optionalGM.isEmpty())
            throw new ClassNotFoundException("Such company id" + id + " not found");
        GM company = optionalGM.get();
        return company;
    }

    @Override
    public GM edit(Long id, CompanyCreateDto dto) throws ClassNotFoundException {
        Optional<GM> optionalGM = companyRepository.findById(id);
        if (optionalGM.isEmpty())
            throw new ClassNotFoundException("Such company id " + id + " not found");
        GM company = optionalGM.get();
        Optional<Address> optionalAddress = addressRepository.findById(dto.getAddressId());
        if (optionalAddress.isEmpty())
            throw new ClassNotFoundException("Such address id " + dto.getAddressId() + " not found");
        Address address = optionalAddress.get();
        Optional<User> optionalUser = userRepository.findById(dto.getDirectorId());
        if (optionalUser.isEmpty())
            throw new ClassNotFoundException("Such user id " + dto.getDirectorId() + " not found");
        User user = optionalUser.get();

        if (dto.getName() != null && !dto.getName().equals(company.getName())) {
            company.setName(dto.getName());
        }
        if (!dto.getAddressId().equals(company.getAddress().getId())) {
            company.setAddress(address);
        }
        if (!dto.getDirectorId().equals(company.getDirector().getId())) {
            company.setDirector(user);
        }
        GM edited = companyRepository.save(company);
        return edited;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<GM> optionalGM = companyRepository.findById(id);
        if (optionalGM.isEmpty())
            throw new ClassNotFoundException("Such company id " + id + " not found");
        companyRepository.deleteById(id);
    }
}
