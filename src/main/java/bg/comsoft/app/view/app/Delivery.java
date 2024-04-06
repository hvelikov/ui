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
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;

@Named("delivery")
//@SessionScoped
@ApplicationScoped
public class Delivery implements Serializable {

    @Getter  LazyDataModel model;
    @Getter List<SortMeta> sortMetaInitial = new ArrayList<>();
    //@Getter List<Firmi> datasource = Firmi.listAll();
    @Getter @Setter Firmi selectedFirmi;


    // https://github.com/apache/myfaces/blob/2.3-next/extensions/quarkus/showcase/src/main/java/org/apache/myfaces/core/extensions/quarkus/showcase/view/LazyCarDataModel.java

    @PostConstruct
        public void init()  {

            Log.info("Initialize LazyDataModel, sort and filter");
            sortMetaInitial.add(SortMeta.builder().field("id").order(SortOrder.DESCENDING).build());
            model = new LazyDataModel() {
                @Override
                public int count(Map filterBy) {
                    @SuppressWarnings("unchecked")
                    Map<String, FilterMeta> filter = (Map<String, FilterMeta>)filterBy;
                    PanacheQuery<PanacheEntityBase> page = getSortAndFilter(filter, Sort.empty());
                    return toIntExact(page.count());
                }

                //@Override
                public List<Firmi> load(int first, int pageSize, Map sortBy, Map filterBy) {
                    @SuppressWarnings("unchecked")
                    Map<String, SortMeta> sortMetaMap = (Map<String, SortMeta>)sortBy;
                    Sort sort = Sort.empty();

                    for (String sKey : sortMetaMap.keySet()) {
                       SortMeta sMeta = (SortMeta)sortBy.get(sKey);
                       if(sMeta.getOrder().name().equalsIgnoreCase("DESCENDING")) {
                           sort.and(sKey, Sort.Direction.Descending);
                       } else
                           sort.and(sKey, Sort.Direction.Ascending);
                    }
                    //PanacheQuery<PanacheEntityBase> page = Firmi.findAll(sort).page(Page.of(first/pageSize,pageSize));
                    @SuppressWarnings("unchecked")
                    Map<String, FilterMeta> filter = (Map<String, FilterMeta>)filterBy;

                    PanacheQuery<PanacheEntityBase> page = getSortAndFilter(filter, sort);
                    return page.page(Page.of(first / pageSize, pageSize)).list();
                }
                List<Firmi> datasource ;

                //@Override
                public Firmi getRowData(String rowKey) {
                    for (Firmi firma : datasource) {
                        //:todo if not Long
                        if ( Long.toString(firma.getId()).equals(rowKey)) {
                            return firma;
                        }
                    }
                    return null;
                }

                @Override
                public String getRowKey(Object object) {
                    if (object instanceof Firmi)
                        return Long.toString(((Firmi) object).getId());
                    return super.getRowKey(object);
                }
            };
            //firmiList = Firmi.listAll();
        }

    private static PanacheQuery<PanacheEntityBase> getSortAndFilter(Map<String, FilterMeta> filter, Sort sort ) {
        String query ="";
        Map<String, Object> params = new HashMap<>();
        for (String sKey : filter.keySet()) {
            FilterMeta fMeta = filter.get(sKey);
            if( fMeta.getFilterValue().toString().length() > 3) {
                query += query.length() < 1 ? "" : " and ";

                if(sKey.equalsIgnoreCase("id")) {
                    query += sKey + " = :" + sKey;
                }  else query += "upper(" + sKey + ") like '%'||:" + sKey + "||'%'";

                params.put(sKey, fMeta.getFilterValue().toString().toUpperCase() );
            }
        }
        PanacheQuery<PanacheEntityBase> page  = Firmi.find(query, sort, params);
        return page;
    }

   /* public void setModel(LazyDataModel model) {
        this.model = model;
    }*/

    public void onRowSelect(SelectEvent<Firmi> event) {
        FacesMessage msg = new FacesMessage("Customer Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)