package bg.comsoft.data.mapper;

import bg.comsoft.data.entity.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link Nalichnosti}
 */
@Getter @Setter
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
    @NotNull
    NalichnostiDto.FirmiDto firmaFid;
    List<NalitemDto> nalitems;

    /**
     * DTO for {@link Firmi}
     */
    @Getter @Setter
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

    /**
     * DTO for {@link Nalitem}
     */
    @Getter @Setter
    public static class NalitemDto implements Serializable {
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
        LocalDate paydate;
        @NotNull
        NalichnostiDto.NalitemDto.ProduktiDto prod;
        List<SerNumDto> serNums;

        /**
         * DTO for {@link Produkti}
         */
        @Getter @Setter
        public static class ProduktiDto implements Serializable {
            Long id;
            @NotNull
            NalichnostiDto.NalitemDto.ProduktiDto.GrupiOtStokiDto gstok;
            @Size(max = 40)
            String partNumber;
            @Size(max = 255)
            String stshort;
            @Size(max = 1024)
            String stdesc;
            BigDecimal priceBay;
            BigDecimal markup;
            BigDecimal priceSale;
            @Size(max = 8)
            String crn;
            Boolean insellst;
            Long inwww;
            Long worm;

            /**
             * DTO for {@link GrupiOtStoki}
             */
            @Getter @Setter
            public static class GrupiOtStokiDto implements Serializable {
                Long id;
                @NotNull
                @Size(max = 80)
                String dshor;
                Long inwww;
            }
        }

        /**
         * DTO for {@link SerNum}
         */
        @Getter @Setter
        public static class SerNumDto implements Serializable {
            Long id;
            @NotNull
            @Size(max = 40)
            String sernum;
            @Size(max = 40)
            String snumDesc;
        }
    }
}