package info.dt.qlcv.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Unit;
import info.dt.qlcv.repository.UnitRepository;

@Service
public class UnitDAO {

	@Autowired
	private UnitRepository unitRepo;
	
	public List<Unit> findAll() {
		return this.unitRepo.findAll();
	}
	
	public Optional<Unit> findById(Integer id) {
		return this.unitRepo.findById(id);
	}
}
