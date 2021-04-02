package info.dt.qlcv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import info.dt.qlcv.entity.ActionDetail;

public interface ActionDetailRepository extends CrudRepository<ActionDetail,Integer> {
	
	@Query(value="SELECT * FROM action_detail as ad where ad.id_topic in ( select id_topic from topic as tp where tp.id_department = ?1);", nativeQuery = true)
	public List<ActionDetail> getActionDetailByIdDepartment(Integer idDepartment);
}
