package info.dt.qlcv.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActionDetail")
    private int id;

    @Column(name = "actionDetailName")
    private String actionDetailName;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "idTopic")
    private Topic topic;
    
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User userCreate;
    
    @ManyToOne
    @JoinColumn(name = "idUser", insertable = false, updatable = false)
    private User userAssign;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;
    
}
