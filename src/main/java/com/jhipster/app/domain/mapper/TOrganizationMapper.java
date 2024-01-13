package com.jhipster.app.domain.mapper;

import com.jhipster.app.domain.TOrganization;
import com.jhipster.app.domain.dto.TOrganizationDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TOrganizationMapper {
	TOrganization dtoToEntity(TOrganizationDTO dto);
	TOrganizationDTO entityToDto(TOrganization entity);
}
