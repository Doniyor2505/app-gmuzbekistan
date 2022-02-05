package uz.digitalone.appgmuzbekistan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.digitalone.appgmuzbekistan.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 6:56 PM
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Region extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double area;

    @Column(nullable = false)
    private Long population;

    public Region(String name, Double area, Long population) {
        this.name = name;
        this.area = area;
        this.population = population;
    }
}
