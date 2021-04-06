package info.dt.qlcv.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.dt.qlcv.entity.Raci;
import info.dt.qlcv.repository.RaciRepository;

@Service
public class RaciDAO {

	@Autowired
	private RaciRepository raciRepo;
	
	public List<Raci> getAll() {
		return raciRepo.findAll();
	}
}
