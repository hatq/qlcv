package info.dt.qlcv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.dt.qlcv.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

	boolean existsUnitByTenDonVi(String tenDonVi);
}
