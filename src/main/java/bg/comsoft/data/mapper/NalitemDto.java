package bg.comsoft.data.mapper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link bg.comsoft.data.entity.Nalitem}
 */
@Value
public class NalitemDto implements Serializable {
    Long id;
    @NotNull
    Long qty;
    @NotNull
    Long seqid;
    BigDecimal priceinUsd;
    BigDecimal pricein;
    Long worm;
    BigDecimal priceSale;
    Long qtyaw;
    Long crn;
    Boolean ispay;
    Boolean ischeck;
    @Size(max = 64)
    String paytype;
    @Size(max = 64)
    String paydoc;
    LocalDate paydate;
}