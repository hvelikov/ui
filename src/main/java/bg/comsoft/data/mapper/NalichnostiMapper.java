package bg.comsoft.data.mapper;

import bg.comsoft.data.entity.Nalichnosti;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface NalichnostiMapper {
    Nalichnosti toEntity(NalichnostiDto nalichnostiDto);

    NalichnostiDto toDto(Nalichnosti nalichnosti);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Nalichnosti partialUpdate(NalichnostiDto nalichnostiDto, @MappingTarget Nalichnosti nalichnosti);
}