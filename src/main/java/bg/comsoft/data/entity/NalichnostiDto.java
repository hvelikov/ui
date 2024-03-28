package bg.comsoft.data.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Nalichnosti}
 */
//@Value
public class NalichnostiDto implements Serializable {
    Long id;
    @NotNull
    NalichnostiDto.FirmiDto firmaFid;
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

    /**
     * DTO for {@link Firmi}
     */
    @Value
    public static class FirmiDto implements Serializable {
        Long id;
        @NotNull
        @Size(max = 55)
        String fname;
        @Size(max = 8)
        String fbakod;
        @Size(max = 10)
        String fbaccnt;
        @Size(max = 50)
        String fbancName;
        @Size(max = 10)
        String fvat;
        @Size(max = 40)
        String fvatDsName;
        @Size(max = 30)
        String fcity;
        @Size(max = 240)
        String fekpoy;
        @Size(max = 40)
        String fstreet;
        @Size(max = 4)
        String fpbox;
        Long fpbkod;
        @Size(max = 30)
        String fcperson;
        @Size(max = 1024)
        String fdesc;
        @Size(max = 1)
        String ftype;
        @Size(max = 4)
        String ftypen;
        @Size(max = 13)
        String fekpo;
        @Size(max = 30)
        String fpolst;
        Long isoapp;
    }
}