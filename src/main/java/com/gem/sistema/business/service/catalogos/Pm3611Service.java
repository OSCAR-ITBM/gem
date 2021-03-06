package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.Pm3611DTO;

public interface Pm3611Service {

	List<Pm3611DTO> save(Pm3611DTO pm3611DTO);

	List<Pm3611DTO> findByTableName(String tableName);

	List<Pm3611DTO> modify(Pm3611DTO pm3611DTO);

	List<Pm3611DTO> delete(Pm3611DTO pm3611DTO);

}
