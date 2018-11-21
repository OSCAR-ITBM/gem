/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcEntidadAdministrativa;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcEntidadAdministrativaRepository.
 *
 * @author gauss
 */
@Repository
public interface TcEntidadAdministrativaRepository
		extends PagingAndSortingRepository<TcEntidadAdministrativa, Long>, QueryDslPredicateExecutor<TcEntidadAdministrativa> {

	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcEntidadAdministrativa> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcEntidadAdministrativa> S save(S entity);
}
