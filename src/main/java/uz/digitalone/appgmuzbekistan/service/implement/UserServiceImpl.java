package uz.digitalone.appgmuzbekistan.service.implement;

import org.springframework.stereotype.Service;
import uz.digitalone.appgmuzbekistan.dto.UserCreateDto;
import uz.digitalone.appgmuzbekistan.entity.Role;
import uz.digitalone.appgmuzbekistan.entity.User;
import uz.digitalone.appgmuzbekistan.repository.RoleRepository;
import uz.digitalone.appgmuzbekistan.repository.UserRepository;
import uz.digitalone.appgmuzbekistan.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserCreateDto dto) throws ClassNotFoundException {

        Set<Role> roleSet = new HashSet<>();
        for (Long roleId : dto.getRoleIds()) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if (optionalRole.isEmpty())
                throw new ClassNotFoundException("Such roles id " + roleId + "");
            Role role = optionalRole.get();
            roleSet.add(role);
        }

        User user = new User(
                dto.getFullName(),
                dto.getEmail(),
                roleSet
        );
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> findAll() {
        List<User> findAll = userRepository.findAll();
        return findAll;
    }

    @Override
    public User findById(Long id) throws ClassNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new ClassNotFoundException("Such user id " + id + " not found");
        User user = optionalUser.get();
        return user;
    }

    @Override
    public User edit(Long id, UserCreateDto dto) throws ClassNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new ClassNotFoundException("Such users id " + id + " not found");
        User user = optionalUser.get();

        Set<Role> roleSet = new HashSet<>();
        for (Long roleId : dto.getRoleIds()) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if(optionalRole.isEmpty())
                throw new ClassNotFoundException("Such role id " + roleId + " not found");
            Role role = optionalRole.get();
            roleSet.add(role);
        }

        if (dto.getFullName() != null && !dto.getFullName().equals(user.getFullName())) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
        }
        if(dto.getRoleIds() != null && !dto.getRoleIds().equals(user.getRoles())){
            user.setRoles(roleSet);
        }
        User edit = userRepository.save(user);
        return edit;
    }

    @Override
    public void delete(Long id) throws ClassNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty())
            throw new ClassNotFoundException("Such address id " + id + " not found");
        userRepository.deleteById(id);
    }

    @Override
    public User bindRole(Long userId, Set<Long> roleIds) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty())
            throw new RuntimeException();
        User user = optionalUser.get();

        Set<Role> roleSet = new HashSet<>();
        for (Long roleId : roleIds) {
            Optional<Role> optionalRole = roleRepository.findById(roleId);
            if(optionalRole.isEmpty())
                throw new RuntimeException();
            Role role = optionalRole.get();
            roleSet.add(role);
        }

        Set<Role> roles = user.getRoles();
        roles.addAll(roleSet);
        user.setRoles(roles);
        User saveBindRole = userRepository.save(user);
        return saveBindRole;

    }

    @Override
    public List<User> findAllRoleId(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if(optionalRole.isEmpty())
            throw new RuntimeException();
        Set<Long> roleSet = new HashSet<>();
        roleSet.add(optionalRole.get().getId());
        List<User> userList = userRepository.selectFindAllRoleId(roleSet);
        return userList;
    }
}
