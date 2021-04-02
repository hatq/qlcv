package info.dt.qlcv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
	
	@Query(value="SELECT * FROM topic as ad where ad.id_department= ?1", nativeQuery = true)
	public List<Topic> getTopicByIdDepartment(Integer idDepartment);
}
