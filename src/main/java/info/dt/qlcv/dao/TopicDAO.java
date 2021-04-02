package info.dt.qlcv.dao;

import info.dt.qlcv.entity.Department;
import info.dt.qlcv.entity.Topic;
import info.dt.qlcv.entity.TypeTopic;
import info.dt.qlcv.model.TopicRequest;
import info.dt.qlcv.repository.DepartmentRepository;
import info.dt.qlcv.repository.TopicRepository;
import info.dt.qlcv.repository.TypeTopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicDAO {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TypeTopicRepository typeTopicRepository;

    public List<Topic> getAllTopic(){
        return (List<Topic>) topicRepository.findAll();
    }
    
    public List<Topic> getTopicByIdDepartment(int id){
        return (List<Topic>) topicRepository.getTopicByIdDepartment(id);
    }
    

    public void insertTopic(TopicRequest topicRequest){

        Topic topic = new Topic();
        topic.setIdTopic(topicRequest.getIdTopic());
        topic.setNameTopic(topicRequest.getNameTopic());
        Optional<TypeTopic> typeTopic = typeTopicRepository.findById(topicRequest.getIdTypeTopic());
        typeTopic.ifPresent(topic::setTypeTopic);

        Optional<Department> department = departmentRepository.findById(topicRequest.getIdDeparment());
        department.ifPresent(topic::setDepartment);

        topicRepository.save(topic);
    }

    public Topic getTopicById(int idTopic){
        return topicRepository.findById(idTopic).orElse(new Topic());
    }

    public boolean deleteTopic(int idTopic){
        try {
            topicRepository.deleteById(idTopic);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }

}
