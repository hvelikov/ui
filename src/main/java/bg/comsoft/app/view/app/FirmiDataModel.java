package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.JpaLazyDataModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import static java.lang.Math.toIntExact;
@Named("dtModel")
@SessionScoped
/**
 * Data Model.
 */
public class FirmiDataModel implements Serializable {
//https://github.com/primefaces/primefaces/issues/8507

    @Getter @Setter JpaLazyDataModel dataModel;

    @Getter @Setter Firmi selectedFirmi;
    @PostConstruct
    public void init() {
     //   dataModel = new JpaLazyDataModel<>(Firmi.class, () -> entityManager, "id");

    }

    public void onRowSelect(SelectEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Customer Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}