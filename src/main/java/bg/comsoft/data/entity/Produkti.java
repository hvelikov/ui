package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PUBLIC)
@Table(name = "PRODUKTI")
public class Produkti extends PanacheEntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "GSTOK_ID", nullable = false)
     GrupiOtStoki gstok;

    @Size(max = 40)
    @Column(name = "PART_NUMBER", length = 40)
     String partNumber;

    @Size(max = 255)
    @Column(name = "STSHORT")
     String stshort;

    @Size(max = 1024)
    @Column(name = "STDESC", length = 1024)
     String stdesc;

    @Column(name = "PRICE_BAY", precision = 10, scale = 2)
     BigDecimal priceBay;

    @Column(name = "MARKUP", precision = 10, scale = 2)
     BigDecimal markup;

    @Column(name = "PRICE_SALE", precision = 10, scale = 2)
     BigDecimal priceSale;

    @Size(max = 8)
    @Column(name = "CRN", length = 8)
     String crn;

    @Column(name = "INSELLST")
     Boolean insellst;

    @Column(name = "INWWW")
     Long inwww;

    @Column(name = "WORM")
    Long worm;

}