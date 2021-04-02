package info.dt.qlcv.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartment")
    private int idDepartment;

    @Column(name = "nameDepartment")
    private String nameDepartment;

    @Column(name = "status")
    private int status;

    @OneToMany(mappedBy = "typeTopic", cascade = CascadeType.ALL)
    private Set<Topic> topicSet;

}
