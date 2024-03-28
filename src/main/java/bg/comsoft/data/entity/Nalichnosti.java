package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//@AllArgsConstructor
@Data
@Entity
//@NamedQueries({
//        @NamedQuery(name = "Nalichnosti.maxCount", query = "select max(n.id) from Nalichnosti n"),
//})
@Table(name = "NALICHNOSTI")
public class Nalichnosti {
    @Id
    @Column(name = "ID", nullable = false)
    public Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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

    //@JsonbTransient
    @OneToMany(mappedBy = "avail", fetch = FetchType.EAGER)
    public List<Nalitem> nalitems = new ArrayList<>();

/*
    @OneToMany(mappedBy = "availId", fetch = FetchType.EAGER)
    //@JoinColumn(name = "ID", nullable = false)
    public List<Nalitem> nalitems = new ArrayList<>();
*/

}