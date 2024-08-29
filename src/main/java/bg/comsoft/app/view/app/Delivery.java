package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.JPALazyDataModel;
import org.primefaces.model.SortMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("delivery")
//@SessionScoped
@ApplicationScoped
public class Delivery implements Serializable {

    @Getter
    JPALazyDataModel<Firmi> model;
    @Getter
    List<SortMeta> sortMetaInitial = new ArrayList<>();  // sortBy
    @Getter
    @Setter
    Firmi selectedFirmi;                         // selection

    @Inject
    EntityManager entityManager;

    // https://github.com/apache/myfaces/blob/2.3-next/extensions/quarkus/showcase/src/main/java/org/apache/myfaces/core/extensions/quarkus/showcase/view/LazyCarDataModel.java

    @PostConstruct
    public void init() {
        Log.info("Initialize LazyDataModel, sort and filter");
        JPALazyDataModel.Builder<Firmi> builder = JPALazyDataModel.builder();
        model = builder.caseSensitive(false)
                .entityClass(Firmi.class).entityManager(() -> entityManager)
                .build();
    }

    public void onRowSelect(SelectEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Customer Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Log.infof("Customer Selected - %s", String.valueOf(event.getObject().getId()));
    }

    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, this::showMessage);
    }

    public void onRowEdit(RowEditEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }


    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        //new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "State has been cleared out", null));
    }

}
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)