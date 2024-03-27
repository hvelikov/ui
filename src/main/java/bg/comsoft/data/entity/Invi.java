package bg.comsoft.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "INVIS")
public class Invi {
    @EmbeddedId
    private InviId id;

    @NotNull
    @Column(name = "QTY", nullable = false)
    private Long qty;

    @NotNull
    @Column(name = "QTYT", nullable = false)
    private Long qtyt;

    @NotNull
    @Column(name = "SALELV", nullable = false, precision = 18, scale = 3)
    private BigDecimal salelv;

    @Column(name = "SALEUSD", precision = 18, scale = 3)
    private BigDecimal saleusd;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PRODUKTI_ID", nullable = false)
    private Produkti produkti;

}