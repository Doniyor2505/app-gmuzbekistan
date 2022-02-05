package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.RegionCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Region;
import uz.digitalone.appgmuzbekistan.repository.RegionRepository;
import uz.digitalone.appgmuzbekistan.service.RegionService;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region save(RegionCreateDto dto) {
        Region region = new Region(
                dto.getName(),
                dto.getArea(),
                dto.getPopulation()
        );
        regionRepository.save(region);
        return region;
    }

    @Override
    public List<Region> findAll() {
        List<Region> regionList = regionRepository.findAll();
        return regionList;
    }

    @Override
    public Region findById(Long id) throws ClassNotFoundException {
        Optional<Region> optionalRegionById = regionRepository.findById(id);
        if(optionalRegionById.isEmpty())
            throw new ClassNotFoundException("Such Region id " + id + " not found");
        Region region = optionalRegionById.get();
        return region;
    }

    @Override
    public Region edit(Long id, RegionCreateDto dto) throws ClassNotFoundException {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isEmpty())
            throw new ClassNotFoundException("Such Region id " + id + " not found");
        Region region = optionalRegion.get();
        if(dto.getArea() != null && !dto.getArea().equals(region.getArea())){
            region.setArea(dto.getArea());
        }
        if(dto.getPopulation() != null && !dto.getPopulation().equals(region.getPopulation())){
            region.setPopulation(dto.getPopulation());
        }
        Region editedRegion = regionRepository.save(region);
        return editedRegion;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if(optionalRegion.isEmpty())
            throw new ClassNotFoundException("Such region id " + id + " not found");
        regionRepository.deleteById(id);
    }
}
