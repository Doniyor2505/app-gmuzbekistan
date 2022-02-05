package uz.digitalone.appgmuzbekistan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.digitalone.appgmuzbekistan.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Author: khamza@nightwell-logistics.com
 * Date: 2/1/2022
 * Time: 6:56 PM
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GM extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Address address;

    @ManyToOne
    private User director;

    @ManyToOne
    private GM parent;      // parent - Filialning Asosiy Companyasi

    public GM(String name, Address address, User director) {
        this.name = name;
        this.address = address;
        this.director = director;
    }
}
