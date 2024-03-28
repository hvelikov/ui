package bg.comsoft.data.mapper;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link bg.comsoft.data.entity.Nalichnosti}
 */
@AllArgsConstructor
@Getter
@Setter
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
}