package bg.comsoft.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SmetkiId implements Serializable {
    private static final long serialVersionUID = 4135550314161897047L;
    @Size(max = 8)
    @NotNull
    @Column(name = "BAE", nullable = false, length = 8)
    private String bae;

    @Size(max = 10)
    @NotNull
    @Column(name = "SMETKA", nullable = false, length = 10)
    private String smetka;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SmetkiId entity = (SmetkiId) o;
        return Objects.equals(this.smetka, entity.smetka) &&
                Objects.equals(this.bae, entity.bae);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smetka, bae);
    }

}