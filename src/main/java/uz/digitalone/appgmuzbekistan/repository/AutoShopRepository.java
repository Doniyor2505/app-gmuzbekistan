package uz.digitalone.appgmuzbekistan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.digitalone.appgmuzbekistan.entity.AutoShop;
import uz.digitalone.appgmuzbekistan.entity.Car;
import uz.digitalone.appgmuzbekistan.entity.Region;

import java.util.List;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 7:33 PM
 */

@Repository
public interface AutoShopRepository extends JpaRepository<AutoShop, Long> {

    @Query(value = "select ashp.* from auto_shop ashp inner join gm on gm.id = ashp.company_id where gm.id = ?1 and gm.name = ?2", nativeQuery = true)
    List<AutoShop> selectByCompanyIdNative(Long companyId, String name);

}
