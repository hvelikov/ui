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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PUBLIC)
@Table(name = "NALITEMS")
public class Nalitem extends PanacheEntityBase {
    @Id
    @Column(name = "NID", nullable = false)
     Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "AVAIL_ID", nullable = false)
    Nalichnosti avail;

    @NotNull
    @Column(name = "QTY", nullable = false)
     Long qty;

    @NotNull
    @Column(name = "SEQID", nullable = false)
    Long seqid;

    @Column(name = "PRICEIN_USD", precision = 10, scale = 2)
     BigDecimal priceinUsd;

    @Column(name = "PRICEIN", precision = 10, scale = 2)
     BigDecimal pricein;

    @Column(name = "WORM")
     Long worm;

    @Column(name = "PRICE_SALE", precision = 10, scale = 2)
     BigDecimal priceSale;

    @Column(name = "QTYAW")
    Long qtyaw;

    @Column(name = "CRN")
     Long crn;

    @Column(name = "ISPAY")
     Boolean ispay;

    @Column(name = "ISCHECK")
     Boolean ischeck;

    @Size(max = 64)
    @Column(name = "PAYTYPE", length = 64)
     String paytype;

    @Size(max = 64)
    @Column(name = "PAYDOC", length = 64)
     String paydoc;

    @Column(name = "PAYDATE")
     LocalDate paydate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "PROD_ID", nullable = false)
    Produkti prod;

    @OneToMany(mappedBy = "nalitemNid")
    List<SerNum> serNums = new ArrayList<SerNum>();
}