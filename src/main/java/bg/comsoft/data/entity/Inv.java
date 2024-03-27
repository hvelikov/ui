package bg.comsoft.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "INVS")
public class Inv {
    @Id
    @Column(name = "\"INV#\"", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "INV_DATE", nullable = false)
    private LocalDate invDate;

    @NotNull
    @Column(name = "INVTYPE", nullable = false)
    private Long invtype;

    @NotNull
    @Column(name = "PSTAT", nullable = false)
    private Long pstat;

    @NotNull
    @Column(name = "PTYPE", nullable = false)
    private Long ptype;

    @Size(max = 255)
    @Column(name = "INV_DESC")
    private String invDesc;

    @Column(name = "CURUSD", precision = 12, scale = 6)
    private BigDecimal curusd;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "SMETKI_BAE", referencedColumnName = "BAE", nullable = false),
            @JoinColumn(name = "SMETKI_SMETKA", referencedColumnName = "SMETKA", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private Smetki smetki;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIRMI_FID", nullable = false)
    private Firmi firmiFid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIRMI_FID_FROM", nullable = false)
    private Firmi firmiFidFrom;

    @Column(name = "VAT")
    private Long vat;

    @Column(name = "KAVNUM")
    private Long kavnum;

}