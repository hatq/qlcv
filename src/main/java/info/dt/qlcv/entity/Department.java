package info.dt.qlcv.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    public Department() {
    }

    public Department(String nameDepartment, int status) {
        this.nameDepartment = nameDepartment;
        this.status = status;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
