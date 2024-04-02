package bg.comsoft.data.mapper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link bg.comsoft.data.entity.Firmi}
 */
@Value
public class FirmiDto implements Serializable {
    Long id;
    @NotNull
    @Size(max = 55)
    String fname;
    @Size(max = 10)
    String fvat;
    @Size(max = 30)
    String fcity;
    @Size(max = 40)
    String fstreet;
    @Size(max = 30)
    String fcperson;
    @Size(max = 30)
    String fpolst;
    List<NalichnostiDto> nalichnostis;
}