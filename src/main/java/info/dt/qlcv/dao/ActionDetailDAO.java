package info.dt.qlcv.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.ActionDetail;
import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.User;
import info.dt.qlcv.model.ActionDetailRequest;
import info.dt.qlcv.repository.ActionDetailRepository;
import info.dt.qlcv.repository.TopicRepository;
import info.dt.qlcv.repository.UserRepository;

@Service
public class ActionDetailDAO {
    @Autowired
    private ActionDetailRepository actionDetailRepository;
    @Autowired
    private TopicRepository topiRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<ActionDetail> getAllActionDetail(){
        return (List<ActionDetail>) actionDetailRepository.findAll();
    }
    public ActionDetail getActionById(int idAction){
        return actionDetailRepository.findById(idAction).orElse(new ActionDetail());
    }
    public List<ActionDetail> getActionDetailByIdDepartment(int id) {
    	return (List<ActionDetail>) actionDetailRepository.getActionDetailByIdDepartment(id);
		
	}
    public boolean deleteAction(int idAction){
        try {
            actionDetailRepository.deleteById(idAction);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
    public int insertActionDetail(ActionDetailRequest actionDetailRequest){

    	int iddepartment= 0;
    	try {
    		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            ActionDetail actionDetail = new ActionDetail();
            actionDetail.setId(actionDetailRequest.getId());
            actionDetail.setStatus(actionDetailRequest.getStatus());
            actionDetail.setActionDetailName(actionDetailRequest.getActionDetailName());
            Date startDate = formatter.parse(actionDetailRequest.getStartDate());
            actionDetail.setStartDate(startDate);
            Date endDate = formatter.parse(actionDetailRequest.getEndDate());
            actionDetail.setEndDate(endDate);
            
            Optional<Topic> topic = topiRepository.findById(actionDetailRequest.getIdTopic());
            if(topic.isPresent())
            	actionDetail.setTopic(topic.get());
            iddepartment = topic.get().getDepartment().getIdDepartment();

            Optional<User> user = userRepository.findById(actionDetailRequest.getIdUser());
            if(user.isPresent())
            	actionDetail.setUserCreate(user.get());
            actionDetailRepository.save(actionDetail);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return iddepartment;
    }
}
