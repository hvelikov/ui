package bg.comsoft.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SMETKI")
public class Smetki {
    @EmbeddedId
    private SmetkiId id;

    @Column(name = "FID")
    private Short fid;

    @Size(max = 11)
    @Column(name = "BULSTAT", length = 11)
    private String bulstat;

    @Size(max = 52)
    @Column(name = "OPIS", length = 52)
    private String opis;

    @Size(max = 10)
    @Column(name = "BIN", length = 10)
    private String bin;

}