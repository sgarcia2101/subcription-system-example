package com.sgarcia.subcription.mapper;

import org.mapstruct.Mapper;

import com.sgarcia.subcription.domain.Subcription;
import com.sgarcia.subcription.entity.SubcriptionEntity;

@Mapper(componentModel = "spring")
public interface SubcriptionEntityMapper {

	public SubcriptionEntity subcriptionToSubcriptioEntity(Subcription subcription);

	public Subcription subcriptionEntityToSubcription(SubcriptionEntity subcription);

}
