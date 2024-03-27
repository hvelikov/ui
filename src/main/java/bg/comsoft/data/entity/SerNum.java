package bg.comsoft.data.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "SER_NUMS")
public class SerNum extends PanacheEntityBase {
    @Id
    @Column(name = "SEQ_NUM", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "NALITEM_NID", nullable = false)
    public Nalitem nalitemNid;

    @Size(max = 40)
    @NotNull
    @Column(name = "SERNUM", nullable = false, length = 40)
    public String sernum;

    @Size(max = 40)
    @Column(name = "SNUM_DESC", length = 40)
    public String snumDesc;

}