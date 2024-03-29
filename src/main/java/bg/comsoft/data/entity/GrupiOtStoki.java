package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;



@FieldDefaults(level = AccessLevel.PUBLIC)
@Entity
@Table(name = "GRUPI_OT_STOKI")
public class GrupiOtStoki extends PanacheEntityBase {
    @Id
    @Column(name = "ID", nullable = false)
    Long id;

    @Size(max = 80)
    @NotNull
    @Column(name = "DSHOR", nullable = false, length = 80)
     String dshor;

    @Column(name = "INWWW")
     Long inwww;

}