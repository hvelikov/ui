package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;

@Named("delivery")
//@SessionScoped
@ApplicationScoped
public class Delivery  implements Serializable  {

    @Getter  JPALazyDataModel    model;

    //@Inject
    @Getter JPALazyDataModel<Firmi> lazyModel;

    @Getter List<SortMeta> sortMetaInitial = new ArrayList<>();

    //@Getter List<Firmi> datasource = Firmi.listAll();
    @Getter @Setter Firmi selectedFirmi;

    @Inject
    EntityManager entityManager;

    // https://github.com/apache/myfaces/blob/2.3-next/extensions/quarkus/showcase/src/main/java/org/apache/myfaces/core/extensions/quarkus/showcase/view/LazyCarDataModel.java

    @PostConstruct
    public void init() {
        Log.info("Initialize LazyDataModel, sort and filter");
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
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)