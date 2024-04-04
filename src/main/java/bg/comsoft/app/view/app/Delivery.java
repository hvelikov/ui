package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import bg.comsoft.data.mapper.FirmiDto;
import bg.comsoft.data.mapper.FirmiMapper;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.FacesException;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.util.Lazy;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;

@Named("dtPaginatorView")
//@SessionScoped
//@Getter
//@Setter
@ApplicationScoped

public class Delivery implements Serializable {

    //private List<FirmiDto> firmiDtos;


        @Inject
        FirmiMapper firmiMapper;

        LazyDataModel model;

        private Firmi selectedFirmi;
        private Firmi[] selectedFirms;

        @PostConstruct
        public void init()  {

            Log.info("Initialize list");
            // https://github.com/apache/myfaces/blob/2.3-next/extensions/quarkus/showcase/src/main/java/org/apache/myfaces/core/extensions/quarkus/showcase/view/LazyCarDataModel.java
            model = new LazyDataModel() {

                private List<Firmi> datasource;

                @Override
                public int count(Map filterBy) {
                    int intExact = toIntExact(Firmi.count());
                    return intExact;
                }

                //@Override
                public List<Firmi> load(int first, int pageSize, Map sortBy, Map filterBy) {

                    Map<String, SortMeta> sortMetaMap = sortBy;
                    Sort sort = Sort.empty();

                    for (String sKey : sortMetaMap.keySet()) {
                       SortMeta sMeta =  (SortMeta)sortBy.get(sKey);
                       if(sMeta.getOrder().name().equalsIgnoreCase("DESCENDING")) {
                           sort.and(sKey, Sort.Direction.Descending);
                       } else
                           sort.and(sKey, Sort.Direction.Ascending);
                    }

                    List<Firmi> fl = Firmi.findAll(sort).page(Page.of(first/pageSize,pageSize)).list();
                    //List<FirmiDto> firmiDtos = firmiMapper.mapList(fl);
                    //return firmiDtos;
                    return fl;
                }

                //@Override
                public Firmi getRowData(String rowKey) {
                    //return Firmi.findById(Long.valueOf(rowKey));
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
        }

    public void setSelectedFirmi(Firmi selectedFirmi) {
        this.selectedFirmi = selectedFirmi;
    }

    public void setSelectedFirms(Firmi[] selectedFirms) {
        this.selectedFirms = selectedFirms;
    }

    public FirmiMapper getFirmiMapper() {
        return firmiMapper;
    }

    public void setFirmiMapper(FirmiMapper firmiMapper) {
        this.firmiMapper = firmiMapper;
    }

    public LazyDataModel getModel() {
        return model;
    }

    public void setModel(LazyDataModel model) {
        this.model = model;
    }

    public Firmi getSelectedFirmi() {
        return selectedFirmi;
    }

    public Firmi[] getSelectedFirms() {
        return selectedFirms;
    }
}
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)