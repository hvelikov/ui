package bg.comsoft.data.mapper1;

import bg.comsoft.data.entity.Nalitem;
import bg.comsoft.data.mapper.NalichnostiDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface NalitemMapper {

    Nalitem toEntity(NalichnostiDto.NalitemDto nalitemDto);

    @AfterMapping
    default void linkSerNums(@MappingTarget Nalitem nalitem) {
        nalitem.getSerNums().forEach(serNum -> serNum.setNalitemNid(nalitem));
    }

    NalichnostiDto.NalitemDto toDto(Nalitem nalitem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Nalitem partialUpdate(NalichnostiDto.NalitemDto nalitemDto, @MappingTarget Nalitem nalitem);
}