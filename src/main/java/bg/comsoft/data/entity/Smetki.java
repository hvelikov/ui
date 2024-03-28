package bg.comsoft.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PUBLIC)
@Entity
@Table(name = "SMETKI")
public class Smetki {
    @EmbeddedId
    SmetkiId id;

    @Column(name = "FID")
    Short fid;

    @Size(max = 11)
    @Column(name = "BULSTAT", length = 11)
    String bulstat;

    @Size(max = 52)
    @Column(name = "OPIS", length = 52)
    String opis;

    @Size(max = 10)
    @Column(name = "BIN", length = 10)
    String bin;

}