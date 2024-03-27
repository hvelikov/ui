package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUKTI")
public class Produkti extends PanacheEntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "GSTOK_ID", nullable = false)
    public GrupiOtStoki gstok;

    @Size(max = 40)
    @Column(name = "PART_NUMBER", length = 40)
    public String partNumber;

    @Size(max = 255)
    @Column(name = "STSHORT")
    public String stshort;

    @Size(max = 1024)
    @Column(name = "STDESC", length = 1024)
    public String stdesc;

    @Column(name = "PRICE_BAY", precision = 10, scale = 2)
    public BigDecimal priceBay;

    @Column(name = "MARKUP", precision = 10, scale = 2)
    public BigDecimal markup;

    @Column(name = "PRICE_SALE", precision = 10, scale = 2)
    public BigDecimal priceSale;

    @Size(max = 8)
    @Column(name = "CRN", length = 8)
    public String crn;

    @Column(name = "INSELLST")
    public Boolean insellst;

    @Column(name = "INWWW")
    public Long inwww;

    @Column(name = "WORM")
    public Short worm;

}