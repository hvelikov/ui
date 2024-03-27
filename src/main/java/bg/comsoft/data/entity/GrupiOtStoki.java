package bg.comsoft.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "GRUPI_OT_STOKI")
public class GrupiOtStoki {
    @Id
    @Column(name = "ID", nullable = false)
    private Short id;

    @Size(max = 80)
    @NotNull
    @Column(name = "DSHOR", nullable = false, length = 80)
    private String dshor;

    @Column(name = "INWWW")
    private Long inwww;

}