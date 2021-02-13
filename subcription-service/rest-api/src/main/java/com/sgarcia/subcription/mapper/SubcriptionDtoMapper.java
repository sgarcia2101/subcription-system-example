package com.sgarcia.subcription.mapper;

import org.mapstruct.Mapper;

import com.sgarcia.subcription.domain.Subcription;
import com.sgarcia.subcription.dto.SubcriptionDTO;

@Mapper(componentModel = "spring")
public interface SubcriptionDtoMapper {

	public SubcriptionDTO subcriptionToSubcriptioDto(Subcription subcription);

	public Subcription subcriptionDtoToSubcription(SubcriptionDTO subcription);

}
