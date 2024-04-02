package bg.comsoft.app.view.app;

import bg.comsoft.data.entity.Firmi;
import bg.comsoft.data.mapper.FirmiDto;
import bg.comsoft.data.mapper.FirmiMapper;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.LazyDataModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static java.lang.Math.toIntExact;

@Named("dtPaginatorView")
//@SessionScoped
@Getter
@Setter
@ApplicationScoped

public class Delivery implements Serializable {

    private List<FirmiDto> firmiDtos;


        @Inject
        FirmiMapper firmiMapper;

        List<FirmiDto> countries;

        LazyDataModel model;

        @PostConstruct
        public void init() {
            Log.info("Initialize list");
            model = new LazyDataModel() {
                @Override
                public int count(Map filterBy) {
                    int intExact = toIntExact(Firmi.count());
                    return intExact;
                }

                @Override
                public List<FirmiDto> load(int first, int pageSize, Map sortBy, Map filterBy) {
                    List<Firmi> fl = Firmi.findAll(Sort.descending("id")).page(Page.of(first/pageSize,pageSize)).list();
                    firmiDtos = firmiMapper.mapList(fl);
                    return firmiDtos;
                }
            };
        }
}
// https://www.javadoc.io/doc/org.primefaces/primefaces/12.0.0/org/primefaces/model/LazyDataModel.html#load(int,int,java.util.Map,java.util.Map)