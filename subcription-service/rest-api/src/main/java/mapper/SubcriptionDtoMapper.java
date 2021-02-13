package mapper;

import org.mapstruct.Mapper;

import domain.Subcription;
import dto.SubcriptionDTO;

@Mapper(componentModel = "spring")
public interface SubcriptionDtoMapper {

	public SubcriptionDTO subcriptionToSubcriptioDto(Subcription subcription);

	public Subcription subcriptionDtoToSubcription(SubcriptionDTO subcription);

}
