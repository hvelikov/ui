package bg.comsoft.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class InviId implements Serializable {
    private static final long serialVersionUID = -6350709699297484956L;
    @NotNull
    @Column(name = "\"INV_INV#\"", nullable = false)
    private Long invInv;

    @NotNull
    @Column(name = "SEQ", nullable = false)
    private Long seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InviId entity = (InviId) o;
        return Objects.equals(this.invInv, entity.invInv) &&
                Objects.equals(this.seq, entity.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invInv, seq);
    }

}