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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "NALITEMS")
public class Nalitem extends PanacheEntityBase {
    @Id
    @Column(name = "NID", nullable = false)
    public Long id;

    @NotNull
    @Column(name = "QTY", nullable = false)
    public Long qty;

    @NotNull
    @Column(name = "SEQID", nullable = false)
    public Short seqid;

    @Column(name = "PRICEIN_USD", precision = 10, scale = 2)
    public BigDecimal priceinUsd;

    @Column(name = "PRICEIN", precision = 10, scale = 2)
    public BigDecimal pricein;

    @Column(name = "WORM")
    public Long worm;

    @Column(name = "PRICE_SALE", precision = 10, scale = 2)
    public BigDecimal priceSale;

    @Column(name = "QTYAW")
    public Short qtyaw;

    @Column(name = "CRN")
    public Long crn;

    @Column(name = "ISPAY")
    public Boolean ispay;

    @Column(name = "ISCHECK")
    public Boolean ischeck;

    @Size(max = 64)
    @Column(name = "PAYTYPE", length = 64)
    public String paytype;

    @Size(max = 64)
    @Column(name = "PAYDOC", length = 64)
    public String paydoc;

    @Column(name = "PAYDATE")
    public LocalDate paydate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PROD_ID", nullable = false)
    public Produkti prod;

    @OneToMany(mappedBy = "nalitemNid")
    public List<SerNum> serNums = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AVAIL_ID")
    public Nalichnosti avail;

}
