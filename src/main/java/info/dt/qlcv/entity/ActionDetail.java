package info.dt.qlcv.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
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
    

    public ActionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActionDetail(int id, String actionDetailName, int status, Date startDate, Date endDate) {
        this.id = id;
        this.actionDetailName = actionDetailName;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActionDetailName() {
        return actionDetailName;
    }

    public void setActionDetailName(String actionDetailName) {
        this.actionDetailName = actionDetailName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

	public User getUserAssign() {
		return userAssign;
	}

	public void setUserAssign(User userAssign) {
		this.userAssign = userAssign;
	}
    
}
