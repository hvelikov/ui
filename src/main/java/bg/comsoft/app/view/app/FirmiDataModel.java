package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.JPALazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("dbModel")
@SessionScoped
/**
 * Data Model.
 */
public class FirmiDataModel implements Serializable {

    @Getter  JPALazyDataModel    model;

    @Getter List<SortMeta> sortMetaInitial = new ArrayList<>();

    @Getter @Setter Firmi selectedFirmi;

    @Inject
    EntityManager entityManager;


    @PostConstruct
    public void init() {
        model = JPALazyDataModel.<Firmi>builder()
                .entityClass(Firmi.class)
                .entityManager(() -> entityManager)
                .build();

        sortMetaInitial.add(SortMeta.builder()
                .field("id")
                .order(SortOrder.DESCENDING)
                .build());

    }

    public void onRowSelect(SelectEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Customer Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}