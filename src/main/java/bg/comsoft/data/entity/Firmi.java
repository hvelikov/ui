package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FIRMI")
public class Firmi extends PanacheEntityBase {
    @Id
    @Column(name = "FID", nullable = false)
    public Long id;

    @Size(max = 55)
    @NotNull
    @Column(name = "FNAME", nullable = false, length = 55)
    public String fname;

    @Size(max = 8)
    @Column(name = "FBAKOD", length = 8)
    public String fbakod;

    @Size(max = 10)
    @Column(name = "\"FBACCNT#\"", length = 10)
    public String fbaccnt;

    @Size(max = 50)
    @Column(name = "FBANC_NAME", length = 50)
    public String fbancName;

    @Size(max = 10)
    @Column(name = "\"FVAT#\"", length = 10)
    public String fvat;

    @Size(max = 40)
    @Column(name = "FVAT_DS_NAME", length = 40)
    public String fvatDsName;

    @Size(max = 30)
    @Column(name = "FCITY", length = 30)
    public String fcity;

    @Size(max = 240)
    @Column(name = "FEKPOY", length = 240)
    public String fekpoy;

    @Size(max = 40)
    @Column(name = "FSTREET", length = 40)
    public String fstreet;

    @Size(max = 4)
    @Column(name = "FPBOX", length = 4)
    public String fpbox;

    @Column(name = "FPBKOD")
    public Long fpbkod;

    @Size(max = 30)
    @Column(name = "FCPERSON", length = 30)
    public String fcperson;

    @Size(max = 1024)
    @Column(name = "FDESC", length = 1024)
    public String fdesc;

    @Size(max = 1)
    @Column(name = "FTYPE", length = 1)
    public String ftype;

    @Size(max = 4)
    @Column(name = "FTYPEN", length = 4)
    public String ftypen;

    @Size(max = 13)
    @Column(name = "FEKPO", length = 13)
    public String fekpo;

    @Size(max = 30)
    @Column(name = "FPOLST", length = 30)
    public String fpolst;

    @Column(name = "ISOAPP")
    public Long isoapp;

}