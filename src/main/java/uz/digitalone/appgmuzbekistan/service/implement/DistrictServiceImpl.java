package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.DistrictCreateDto;
import uz.digitalone.appgmuzbekistan.entity.District;
import uz.digitalone.appgmuzbekistan.entity.Region;
import uz.digitalone.appgmuzbekistan.repository.DistrictRepository;
import uz.digitalone.appgmuzbekistan.repository.RegionRepository;
import uz.digitalone.appgmuzbekistan.service.DistrictService;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final RegionRepository regionRepository;
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               RegionRepository regionRepository) {
        this.districtRepository = districtRepository;
        this.regionRepository = regionRepository;
    }


    @Override
    public District save(DistrictCreateDto dto) throws ClassNotFoundException {
        Optional<Region> optionalRegion = regionRepository.findById(dto.getRegionId());
        if(optionalRegion.isEmpty())
            throw new ClassNotFoundException("Such Region id " + dto.getRegionId() + " not found");
        Region region = optionalRegion.get();
        District district = new District(
                dto.getName(),
                dto.getArea(),
                dto.getPopulation(),
                region
        );
        District savedDistrict = districtRepository.save(district);
        return savedDistrict;
    }

    @Override
    public List<District> findAll() {
        List<District> districtList = districtRepository.findAll();
        return districtList;
    }

    @Override
    public District findById(Long id) throws ClassNotFoundException {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if(optionalDistrict.isEmpty())
            throw new ClassNotFoundException("Such District id " + id + " not found");
        District district = optionalDistrict.get();
        return district;
    }

    @Override
    public District edit(Long id, DistrictCreateDto dto) throws ClassNotFoundException {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if(optionalDistrict.isEmpty())
            throw new ClassNotFoundException("Such District id " + id + " not found");

        Optional<Region> optionalRegion = regionRepository.findById(dto.getRegionId());
        if(optionalRegion.isEmpty())
            throw new ClassNotFoundException("Such Region id " + dto.getRegionId() + " not found");
        Region region = optionalRegion.get();

        District district = optionalDistrict.get();

        if(dto.getName() != null && !dto.getName().equals(district.getName())){
            district.setName(dto.getName());
        }

        if (dto.getArea() != null && !dto.getArea().equals(district.getArea())) {
            district.setArea(dto.getArea());
        }

        if (dto.getPopulation() != null && !dto.getPopulation().equals(district.getPopulation())) {
            district.setPopulation(dto.getPopulation());
        }
        if(dto.getRegionId() != district.getRegion().getId()){
            district.setRegion(region);
        }

        District editDistrict = districtRepository.save(district);
        return editDistrict;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        if(optionalDistrict.isEmpty())
            throw new ClassNotFoundException("Such district id " + id + " not found");
        districtRepository.deleteById(id);
    }
}
