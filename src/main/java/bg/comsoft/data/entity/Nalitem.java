package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NALITEMS")
public class Nalitem extends PanacheEntityBase {
    @Id
    @Column(name = "NID", nullable = false)
    public Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "AVAIL_ID", nullable = false)
    public Nalichnosti avail;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PROD_ID", nullable = false)
    public Produkti prod;

    @OneToMany(mappedBy = "nalitemNid")
    public List<SerNum> serNums = new ArrayList<>();
}

/*
   @Id
    @Column(name = "NID", nullable = false)
    public Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "AVAIL_ID", nullable = false)
    public Nalichnosti avail;

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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PROD_ID", nullable = false)
    public Produkti prod;

 */