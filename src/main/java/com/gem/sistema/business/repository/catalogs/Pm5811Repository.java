package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5811Repository.
 */
@Repository("pm5811Repository")
public interface Pm5811Repository extends PagingAndSortingRepository<Pm5811, Long>, QueryDslPredicateExecutor<Pm5811> {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5811> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5811> findAllByIdsector(Integer idSector);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm5811> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5811> S save(S entity);

	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5811> findAllByIdsectorOrderBySemesAsc(Integer idSector);

	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm5811 where idsector =:idSector and semes =:semestre")
	Integer countBySemestre(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);

	/**
	 * Find all bysemes and idsector.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return the pm 5811
	 */
	Pm5811 findAllBysemesAndIdsector(Integer semestre, Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm5811 where idsector =:idSector")
	Integer count(@Param("idSector") Integer idSector);
}
