package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.RoleCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Role;
import uz.digitalone.appgmuzbekistan.repository.RoleRepository;
import uz.digitalone.appgmuzbekistan.service.RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(RoleCreateDto dto) {
        Role role = new Role(
                dto.getName()
        );
        Role savedRole = roleRepository.save(role);
        return savedRole;
    }

    @Override
    public List<Role> findAll() {
        List<Role> findAllRole = roleRepository.findAll();
        return findAllRole;
    }

    @Override
    public Role findById(Long id) throws ClassNotFoundException {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty())
            throw new ClassNotFoundException("Such role id " + id + " not found");
        Role role = optionalRole.get();
        return role;
    }

    @Override
    public Role edit(Long id, RoleCreateDto dto) throws ClassNotFoundException {

        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isEmpty())
            throw new ClassNotFoundException("Such role id " + id + " not fonud");
        Role role = optionalRole.get();
        if(!dto.getName().equals(role.getName())){
            role.setName(dto.getName());
        }
        Role saved = roleRepository.save(role);
        return saved;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isEmpty())
            throw new ClassNotFoundException("Such role id " + id + " not found");
        roleRepository.deleteById(id);
    }
}
