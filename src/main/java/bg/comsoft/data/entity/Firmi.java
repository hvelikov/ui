package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)

@Entity
@Table(name = "FIRMI")
public class Firmi extends PanacheEntityBase {
    @Id
    @Column(name = "FID", nullable = false)
    Long id;

    @Size(max = 55)
    @NotNull
    @Column(name = "FNAME", nullable = false, length = 55)
     String fname;

    @Size(max = 8)
    @Column(name = "FBAKOD", length = 8)
     String fbakod;

    @Size(max = 10)
    @Column(name = "\"FBACCNT#\"", length = 10)
     String fbaccnt;

    @Size(max = 50)
    @Column(name = "FBANC_NAME", length = 50)
     String fbancName;

    @Size(max = 10)
    @Column(name = "\"FVAT#\"", length = 10)
     String fvat;

    @Size(max = 40)
    @Column(name = "FVAT_DS_NAME", length = 40)
     String fvatDsName;

    @Size(max = 30)
    @Column(name = "FCITY", length = 30)
     String fcity;

    @Size(max = 240)
    @Column(name = "FEKPOY", length = 240)
     String fekpoy;

    @Size(max = 40)
    @Column(name = "FSTREET", length = 40)
     String fstreet;

    @Size(max = 4)
    @Column(name = "FPBOX", length = 4)
     String fpbox;

    @Column(name = "FPBKOD")
     Long fpbkod;

    @Size(max = 30)
    @Column(name = "FCPERSON", length = 30)
     String fcperson;

    @Size(max = 1024)
    @Column(name = "FDESC", length = 1024)
     String fdesc;

    @Size(max = 1)
    @Column(name = "FTYPE", length = 1)
     String ftype;

    @Size(max = 4)
    @Column(name = "FTYPEN", length = 4)
     String ftypen;

    @Size(max = 13)
    @Column(name = "FEKPO", length = 13)
     String fekpo;

    @Size(max = 30)
    @Column(name = "FPOLST", length = 30)
     String fpolst;

    @Column(name = "ISOAPP")
     Long isoapp;

}