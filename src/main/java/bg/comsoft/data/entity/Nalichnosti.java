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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// https://quarkus.io/guides/hibernate-orm-panache
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)

@Entity

@NamedQueries({
        @NamedQuery(name = "Nalichnosti.getByFirm", query = "from Nalichnosti, Firmi where firmaFid.id = ?1"),
        @NamedQuery(name = "Nalichnosti.count", query = "select count(*) from Nalichnosti n where n.id = ?1"),
        @NamedQuery(name = "Nalichnosti.getFirmiLikeName", query = "select f from Firmi f where upper(f.fname) like '%'||?1||'%' order by f.fname asc")
     })

@Table(name = "NALICHNOSTI")
public class Nalichnosti extends PanacheEntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    Long id;

    @Column(name = "DATE_IN")
    LocalDate dateIn;

    @Size(max = 8)
    @Column(name = "\"KARTA#\"", length = 8)
    String karta;

    @Size(max = 8)
    @Column(name = "CRN", length = 8)
    String crn;

    @Size(max = 8)
    @Column(name = "STATUS", length = 8)
    String status;

    @Size(max = 255)
    @Column(name = "AVAIL_DESC")
    String availDesc;

    @Column(name = "CRNDAT")
    Long crndat;

    @Column(name = "ISPAY")
    Boolean ispay;

    @Column(name = "ISCHECK")
    Boolean ischeck;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "FIRMA_FID", nullable = false)
    Firmi firmaFid;

    @OneToMany(mappedBy = "avail")
    private List<Nalitem> nalitems = new ArrayList<>();

   /*   @JsonIgnoreProperties("true")
        @OneToMany(mappedBy = "avail")
        List<Nalitem> nalitems = new ArrayList<Nalitem>();
   */
}