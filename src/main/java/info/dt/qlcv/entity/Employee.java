package info.dt.qlcv.entity;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEmployee")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "point")
    private double point;

    @Column(name = "note")
    private String note;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Employee() {
    }

    public Employee(String firstName, String lastName, double point, String note, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.point = point;
        this.note = note;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
