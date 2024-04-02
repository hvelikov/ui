package bg.comsoft.data.mapper;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link bg.comsoft.data.entity.Nalichnosti}
 */
@Value
public class NalichnostiDto implements Serializable {
    Long id;
    LocalDate dateIn;
    @Size(max = 8)
    String karta;
    @Size(max = 8)
    String crn;
    @Size(max = 8)
    String status;
    @Size(max = 255)
    String availDesc;
    Long crndat;
    Boolean ispay;
    Boolean ischeck;
    List<NalitemDto> nalitems;
}