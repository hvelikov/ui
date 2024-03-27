package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
//@NamedQueries({
//        @NamedQuery(name = "Nalichnosti.maxCount", query = "select max(n.id) from Nalichnosti n"),
//})
@Table(name = "NALICHNOSTI")
public class Nalichnosti extends PanacheEntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIRMA_FID", nullable = false)
    public Firmi firmaFid;

    @Column(name = "DATE_IN")
    public LocalDate dateIn;

    @Size(max = 8)
    @Column(name = "\"KARTA#\"", length = 8)
    public String karta;

    @Size(max = 8)
    @Column(name = "CRN", length = 8)
    public String crn;

    @Size(max = 8)
    @Column(name = "STATUS", length = 8)
    public String status;

    @Size(max = 255)
    @Column(name = "AVAIL_DESC")
    public String availDesc;

    @Column(name = "CRNDAT")
    public Long crndat;

    @Column(name = "ISPAY")
    public Boolean ispay;

    @Column(name = "ISCHECK")
    public Boolean ischeck;
}