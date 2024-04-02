package bg.comsoft.data.mapper;

import bg.comsoft.data.entity.Firmi;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface FirmiMapper {
    Firmi toEntity(FirmiDto firmiDto);

    @AfterMapping
    default void linkNalichnostis(@MappingTarget Firmi firmi) {
        firmi.getNalichnostis().forEach(nalichnosti -> nalichnosti.setFirmaFid(firmi));
    }

    FirmiDto toDto(Firmi firmi);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Firmi partialUpdate(FirmiDto firmiDto, @MappingTarget Firmi firmi);

    List<FirmiDto> mapList(List<Firmi> firms);
}