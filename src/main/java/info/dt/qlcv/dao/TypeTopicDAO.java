package info.dt.qlcv.dao;

import info.dt.qlcv.entity.TypeTopic;
import info.dt.qlcv.repository.TypeTopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTopicDAO {
    @Autowired
    private TypeTopicRepository typeTopicRepository;


    public TypeTopic insertTypeTopic(TypeTopic typeTopic){

        try {
            return  typeTopicRepository.save(typeTopic);
        }
        catch (Exception ex){
            return null;
        }
    }
    public TypeTopic getTypeTopicById(int idTypeTopic){
        return typeTopicRepository.findById(idTypeTopic).orElse(new TypeTopic());
    }
    public List<TypeTopic> getAllTypeTopic (){
        return (List<TypeTopic>) typeTopicRepository.findAll();
    }

    public boolean deleteTypeTopic(int idTypeTopic){
        try {
            typeTopicRepository.deleteById(idTypeTopic);
            return true;
        }
        catch (Exception ex){
            return false;
        }
    }
}
