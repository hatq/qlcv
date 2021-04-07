package info.dt.qlcv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.dt.qlcv.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Integer> {

}
