package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
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

@Getter
@Setter

@Named("delivery")
//@SessionScoped
@ApplicationScoped
public class Delivery implements Serializable {

//        @Inject
//        FirmiMapper firmiMapper;

        LazyDataModel model;

        public List<SortMeta> sortMetaInitial = new ArrayList<>();

        List<Firmi> firmiList;

    // https://github.com/apache/myfaces/blob/2.3-next/extensions/quarkus/showcase/src/main/java/org/apache/myfaces/core/extensions/quarkus/showcase/view/LazyCarDataModel.java

    @PostConstruct
        public void init()  {

            Log.info("Initialize list");
            sortMetaInitial.add(
                    SortMeta.builder()
                    .field("id").order(SortOrder.DESCENDING)
                    .build());

            model = new LazyDataModel() {

                private List<Firmi> datasource;

                @Override
                public int count(Map filterBy) {
                   // int intExact = toIntExact(Firmi.count());
                    PanacheQuery<PanacheEntityBase> page = getSortAndFilter(filterBy, Sort.empty());
                    int intExact = toIntExact(page.count());
                    return intExact;
                }

                //@Override
                public List<Firmi> load(int first, int pageSize, Map sortBy, Map filterBy) {

                    Map<String, SortMeta> sortMetaMap = sortBy;
                    Sort sort = Sort.empty();

                    for (String sKey : sortMetaMap.keySet()) {
                       SortMeta sMeta = (SortMeta)sortBy.get(sKey);
                       if(sMeta.getOrder().name().equalsIgnoreCase("DESCENDING")) {
                           sort.and(sKey, Sort.Direction.Descending);
                       } else
                           sort.and(sKey, Sort.Direction.Ascending);
                    }
                    //PanacheQuery<PanacheEntityBase> page = Firmi.findAll(sort).page(Page.of(first/pageSize,pageSize));

                    Map<String, FilterMeta> filter = filterBy;

                    PanacheQuery<PanacheEntityBase> page = getSortAndFilter(filter, sort);
                    return page.page(Page.of(first / pageSize, pageSize)).list();
                }

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
                public String getRowKey(Firmi firma) {
                    return Long.toString( firma.getId() );
                }
            };
            firmiList = Firmi.listAll();
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

/*
    @Override
    public void afterPhase(PhaseEvent event) {
        System.err.println((System.currentTimeMillis() - start) + "ms");
    }
    private long start;
    @Override
    public void beforePhase(PhaseEvent event) {
        start = System.currentTimeMillis();
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }*/
}
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)