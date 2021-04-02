package info.dt.qlcv.model;


import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.User;
import lombok.Data;

@Data
public class ActionDetailRequest {

	private int id;
	private String actionDetailName;
	private int status;
	private int idTopic;
	private Topic topic;
	private User userCreate;
	private int idUser;
	private User userAssign;
	private String startDate;
	private String endDate;
	
}
