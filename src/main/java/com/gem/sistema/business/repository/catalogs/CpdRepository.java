package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Cpd;;

// TODO: Auto-generated Javadoc
/**
 * The Interface CpdRepository.
 *
 * @author 
 */
@Repository
public interface CpdRepository extends PagingAndSortingRepository<Cpd, Long>, QueryDslPredicateExecutor<Cpd> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Cpd> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Cpd> S save(S entity);

	/**
	 * Find by pe and nope and tema.
	 *
	 * @param pe the pe
	 * @param nope the nope
	 * @param tema the tema
	 * @return the list
	 */
	List<Cpd> findByPeAndNopeAndTema(String pe, String nope, String tema);

	/**
	 * Find by cvetemas.
	 *
	 * @param tema the tema
	 * @return the list
	 */
	List<Cpd> findByCvetemas(String tema);

}
