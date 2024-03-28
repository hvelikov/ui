package bg.comsoft.test;

import bg.comsoft.data.entity.*;
import bg.comsoft.data.mapper.NalichnostiDto;
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
        // get last inventory record
        Nalichnosti delivery = getLastNalichnosti();

        //List<Nalitem> deliveryRecord = Nalitem.find("avail.id=?1", delivery.id).list();

        List<Nalitem> deliveryRecord = delivery.nalitems;
        Firmi deliveryFrm = delivery.firmaFid;

        Log.infof("\n Delivery id: %s, dateIn: %s, karta: %s \n Firma Id: %s, fname: %s", delivery.id, delivery.dateIn, delivery.karta, deliveryFrm.id,  deliveryFrm.fname);

        for(Nalitem nalitem :  deliveryRecord) {
            Produkti prod = nalitem.prod;
            Log.infof("%s, %s, %s,  %s, %s", prod.partNumber, prod.stshort,  nalitem.qty,  nalitem.worm, nalitem.pricein);
            for (SerNum serNum : nalitem.serNums) {
               Log.infof("%s,  %s ", serNum.sernum, serNum.snumDesc);
            }
        }
    }

    @Order(2)
    //@Disabled
    @Test
    @Transactional
    public void deliveryToJson() {

        Log.infof("\n ********* Deliveries %s, Firms %s  *************", Nalichnosti.count(), Firmi.count());
        // get last inventory record

        Nalichnosti delivery = getLastNalichnosti();

        //Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        //System.out.println( jsonb.toJson(delivery));

        delivery.karta = "10360";
        delivery.status = "OK";
        delivery.firmaFid.fpbox = "111";
        Nalitem nalitem = delivery.nalitems.get(1);
        nalitem.paytype="Invoice1";
//        nalitem.serNums.get(1).snumDesc = "@";
//        nalitem.serNums.get(2).snumDesc = "@!";
        nalitem.serNums.get(1).snumDesc = "10360";
        nalitem.serNums.get(2).snumDesc = "10360";
        nalitem.prod.stdesc="--";



        Log.infof("\n Delivery id: %s, dateIn: %s, karta: %s \n Firma Id: %s, fname: %s", delivery.id, delivery.dateIn, delivery.karta, delivery.firmaFid.id,  delivery.firmaFid.fname);
    }

    @Inject
    NalichnostiMapper nalichnostiMapper;
    @Order(3)
    //@Disabled
    @Test
    @Transactional
    public void deliveryToDto() {

        Log.infof("\n ********* Deliveries %s, Firms %s  *************", Nalichnosti.count(), Firmi.count());
        // get last inventory record

        Nalichnosti delivery = getLastNalichnosti();

        NalichnostiDto nalichnostiDto = nalichnostiMapper.toDto(delivery);
        //nalichnostiDto.firmaFid.fvatDsName="****";
        nalichnostiDto.setStatus("****");
        Nalichnosti delivery1 = nalichnostiMapper.partialUpdate(nalichnostiDto, delivery);

        Log.infof("\n Delivery id: %s, dateIn: %s, karta: %s \n Firma Id: %s, fname: %s", delivery.id, delivery.dateIn, delivery.karta, delivery.firmaFid.id,  delivery.firmaFid.fname);
    }

    private static Nalichnosti getLastNalichnosti() {
        Query nativeQuery = Nalichnosti.getEntityManager().createNativeQuery("select max(id) from NALICHNOSTI");
        BigDecimal rId = (BigDecimal)nativeQuery.getSingleResult();
        Nalichnosti delivery = Nalichnosti.findById( rId.longValue());
        return delivery;
    }
}



