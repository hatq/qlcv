package info.dt.qlcv.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTopic")
    private int idTopic;

    @Column(name = "nameTopic")
    private String nameTopic;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "idTypeTopic")
    private TypeTopic typeTopic;

    @ManyToOne
    @JoinColumn(name = "idDepartment")
    private Department department;

}
