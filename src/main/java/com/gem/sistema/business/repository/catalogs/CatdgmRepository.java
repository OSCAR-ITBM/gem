package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catdgm;;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatdgmRepository.
 */
@Repository(value = "catdgmRepository")
public interface CatdgmRepository extends PagingAndSortingRepository<Catdgm, Long>, QueryDslPredicateExecutor<Catdgm>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catdgm> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catdgm> S save(S entity);
	
	/**
	 * Find by clave.
	 *
	 * @param clave the clave
	 * @return the list
	 */
	List<Catdgm> findByClave(String clave);
	
	/**
	 * Método para ordenar por clave.
	 *
	 * @return the list
	 */
	List<Catdgm> findAllByOrderByClave();
	
	/**
	 * Gets the by clave.
	 *
	 * @param clave the clave
	 * @return the by clave
	 */
	@Query("select c from Catdgm c where clave = :clave ")
	Catdgm getByClave(@Param("clave")String clave);
}
