package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catdep;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatdepRepository.
 */
@Repository("catdepRepository")
public interface CatdepRepository extends PagingAndSortingRepository<Catdep, Long>, QueryDslPredicateExecutor<Catdep> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catdep> findAll();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catdep> S save(S entity);

	/* (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
	 */
	List<Catdep> findAll(Predicate prdct);

	/**
	 * Find all by idsector order by clvdep.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Catdep> findAllByIdsectorOrderByClvdep(Integer idSector);

	/**
	 * Find all by idsector and ultniv order by clvdep.
	 *
	 * @param idSector the id sector
	 * @param ultniv the ultniv
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Catdep> findAllByIdsectorAndUltnivOrderByClvdep(Integer idSector, String ultniv);

	/**
	 * Find first by idsector and clvdep and ultniv order by id asc.
	 *
	 * @param idSector the id sector
	 * @param clvdep the clvdep
	 * @param ultniv the ultniv
	 * @return the catdep
	 */
	Catdep findFirstByIdsectorAndClvdepAndUltnivOrderByIdAsc(Integer idSector, String clvdep, String ultniv);
	
	/**
	 * Find first by idsector and clvdep.
	 *
	 * @param idSector the id sector
	 * @param clvdep the clvdep
	 * @return the catdep
	 */
	Catdep findFirstByIdsectorAndClvdep(Integer idSector, String clvdep);

  /**
   * Find first by idsector and clvdep order by id asc.
   *
   * @param idSector the id sector
   * @param clvdep the clvdep
   * @return the catdep
   */
  Catdep findFirstByIdsectorAndClvdepOrderByIdAsc(Integer idSector,String clvdep);

  /**
   * Find all dependencies.
   *
   * @param idSector the id sector
   * @param clvdep the clvdep
   * @return the list
   */
  @Query("select c from Catdep c WHERE upper(c.clvdep) like :clvdep% and c.ultniv = 'S' and c.idsector = :idSector order by c.clvdep asc")
  List<Catdep> findAllDependencies(@Param("idSector")Integer idSector, @Param("clvdep")String clvdep);

	/**
	 * Find by clvdep.
	 *
	 * @param clvdep the clvdep
	 * @return the list
	 */
	List<Catdep> findByClvdep(String clvdep);

	/**
	 * Find by nomdep.
	 *
	 * @param nomdep the nomdep
	 * @return the list
	 */
	List<Catdep> findByNomdep(String nomdep);

	/**
	 * Find by clvdep and nomdep.
	 *
	 * @param clvdep the clvdep
	 * @param nomdep the nomdep
	 * @return the list
	 */
	List<Catdep> findByClvdepAndNomdep(String clvdep, String nomdep);

	/**
	 * Find all by order by clvdep.
	 *
	 * @return the list
	 */
	List<Catdep> findAllByOrderByClvdep();

	// List<Catdep> findByNota(String nota);

	/**
	 * Find all by id sector cross xcatpro.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "SELECT ROW_NUMBER() OVER()  AS ID, CLVDEP, NOMDEP, '' as CAPDEP, '' as CARGO_E, "
			+ "'' as CARGO_T, '' as ELABORO, null as FECCAP, null as ID_REF, IDSECTOR, '' as LAUTORIZO, "
			+ "'' as LREVISO, '' as LTITULO1, '' as LTITULO2, '' as NAUTORIZO, '' as NREVISO, ULTNIV, '' as USERID, "
			+ "'' as NELABORO, '' as NTITULO1, '' as NTITULO2, '' as TITULAR, '' as LELABORO  FROM ( "
			+ "SELECT DISTINCT C.CLVDEP AS CLVDEP, C.NOMDEP AS NOMDEP, C.IDSECTOR AS IDSECTOR, C.ULTNIV AS ULTNIV "
			+ "FROM CATDEP C INNER JOIN XCATPRO X ON X.CLVDEP=C.CLVDEP AND LOWER(C.ULTNIV) = 's' AND C.IDSECTOR=:idSector "
			+ "ORDER BY 1)", nativeQuery = true)
	List<Catdep> findAllByIdSectorCrossXcatpro(@Param("idSector") Integer idSector);

	/**
	 * Exist by clvdep.
	 *
	 * @param idSector the id sector
	 * @param scta the scta
	 * @return the boolean
	 */
	@Query(value = "SELECT CASE  WHEN COUNT(1)> 0 THEN 'true' ELSE 'false' END " + "FROM CATDEP C "
			+ "INNER JOIN XCATPRO X "
			+ "ON X.CLVDEP=C.CLVDEP AND C.CLVDEP=:scta AND LOWER(C.ULTNIV) = 's' AND C.IDSECTOR=:idSector", nativeQuery = true)
	Boolean existByClvdep(@Param("idSector") Integer idSector, @Param("scta") String scta);

	/**
	 * Gets the clvdep by idsector.
	 *
	 * @param idsector the idsector
	 * @return the clvdep by idsector
	 */
	@Query("select substr(clvdep, 1,5)  from Catdep  where idsector=:idsector and clvdep like '%00000'")
	List<String> getClvdepByIdsector (@Param ("idsector") Integer idsector);

	/**
	 * Gets the clvdep 3 by idsector.
	 *
	 * @param idsector the idsector
	 * @return the clvdep 3 by idsector
	 */
	@Query("select distinct substr(clvdep, 1,3)  from Catdep  where idsector=:idsector and clvdep like '%0000000'")
	List<String> getClvdep3ByIdsector (@Param ("idsector") Integer idsector);

	/**
	 * Find by clvdep and idsector.
	 *
	 * @param clvdep the clvdep
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Catdep> findByClvdepAndIdsector(String clvdep, Integer idSector);
}
