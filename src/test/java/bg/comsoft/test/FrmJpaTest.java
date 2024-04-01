package bg.comsoft.test;

import bg.comsoft.data.entity.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.logging.Log;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

import static bg.comsoft.data.entity.Nalichnosti.*;

@QuarkusTest
//@Disabled
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1)
public class FrmJpaTest {
    @Order(1) //@Disabled
    @Test
    //@Transactional
    public void registerDelivery() {
        Log.infof("\n ********* Deliveries %s, Firms %s  *************", Nalichnosti.count(), Firmi.count());

        PanacheQuery<PanacheEntityBase> query = Nalichnosti.find("#Nalichnosti.getFirmiLikeName", "œŒ  ");
        List<Firmi> f = query.list();
        // get last inventory record
        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360
        Nalichnosti lastDeliveryRecord = Nalichnosti.find("id = ?1", maxDelivery.id ).firstResult(); // record with last 10360
        printDelivered(lastDeliveryRecord);
    }


    @Order(2)
    //@Disabled
    @Test
    //@Transactional
    public void deliveryToJsonDto() {

        Log.infof("********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
        // get last inventory record
        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360
        Nalichnosti lastDeliveryRecord = Nalichnosti.find("id = ?1", maxDelivery.id ).firstResult(); // record with last 10360

        Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        System.out.println( jsonb.toJson(jsonb.toJson(lastDeliveryRecord)) );

        logStoreRecord(maxDelivery);
    }

    private static Nalichnosti getLastNalichnosti() {
        Query nativeQuery = getEntityManager().createNativeQuery("select max(id) from NALICHNOSTI");
        BigDecimal rId = (BigDecimal)nativeQuery.getSingleResult();
        Nalichnosti delivery = findById( rId.longValue());
        return delivery;
    }

    private static void printDelivered(Nalichnosti deliveredRecord) {
        logStoreRecord(deliveredRecord);
        Log.infof("==> %s %s %s", deliveredRecord.id, deliveredRecord.status, deliveredRecord.ischeck);
        for( Nalitem nalitem : deliveredRecord.getNalitems() ) {
            Produkti prod = nalitem.prod;
            Log.infof( "%-15s, %-70.70s, %6s,  %4s, %8s  ===> %s %s %s <===", nalitem.prod.partNumber, prod.stshort, nalitem.qty, nalitem.worm, nalitem.pricein,
                    nalitem.id, nalitem.ischeck, nalitem.paytype);
            for (SerNum serNum : nalitem.serNums) {
                Log.infof("| %-10s | %-10s | %10s |", serNum.id, serNum.sernum, serNum.snumDesc);
            }
        }
    }

    private static void logStoreRecord(Nalichnosti maxDelivery) {
        //Log.infof("\n ********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
        Log.infof("Delivery id: %s, dateIn: %s, karta: %s. Firma Id: %s, fname: %s", maxDelivery.id, maxDelivery.dateIn, maxDelivery.karta, maxDelivery.firmaFid.id,  maxDelivery.firmaFid.fname);
    }

}



