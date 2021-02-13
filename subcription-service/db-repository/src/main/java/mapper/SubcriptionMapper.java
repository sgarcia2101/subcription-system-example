package mapper;

import org.mapstruct.Mapper;

import domain.Subcription;
import entity.SubcriptionEntity;

@Mapper(componentModel = "spring")
public interface SubcriptionMapper {

	public SubcriptionEntity subcriptionToSubcriptioEntity(Subcription subcription);

	public Subcription subcriptionEntityToSubcription(SubcriptionEntity subcription);

}
