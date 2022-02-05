package uz.digitalone.appgmuzbekistan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.digitalone.appgmuzbekistan.entity.Region;
import uz.digitalone.appgmuzbekistan.entity.User;

import java.util.List;
import java.util.Set;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:33 PM
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u" +
            "inner join role_users ru on ru.users_id = u.id " +
            "inner join roles r on r.id = ru.role_id where r.id = ?1", nativeQuery = true)
    List<User> selectFindAllRoleId(Set<Long> roleId);


}
