package com.bilgeadam.springbootthymeleaf.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bilgeadam.springbootthymeleaf.model.Konu;

@Repository
@Transactional
public interface KonuRepository extends JpaRepository<Konu, Long> {
	@Query(name = "findByKonuName", value = "SELECT * FROM obsh.konu WHERE NAME LIKE :NAME", nativeQuery = true)
	public List<Konu> findByKonuName(@Param("NAME") String name);

	public List<Konu> findAllByNAMELike(String name);

	public List<Konu> findAllByNAMELike(String expression, org.springframework.data.domain.Sort sort);

	public List<Konu> findAllByNAMELikeOrderByIDDesc(String name);

}
