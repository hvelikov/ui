package bg.comsoft.test;

import bg.comsoft.data.entity.*;
import bg.comsoft.data.mapper.NalichnostiDto;
import bg.comsoft.data.mapper.NalichnostiDto.NalitemDto;
import bg.comsoft.data.mapper.NalichnostiMapper;
import bg.comsoft.data.mapper1.NalitemMapper;
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

        // get last inventory record
        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360
        Nalichnosti lastDeliveryRecord = Nalichnosti.find("id = ?1", maxDelivery.id ).firstResult(); // record with last 10360
        printDelivered(lastDeliveryRecord);
    }


    @Order(2)
    //@Disabled
    @Test
    @Transactional
    public void deliveryUpdateDto() {

        Log.infof("\n ********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
        // get last inventory record

        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360
        Nalichnosti deliveredRecord = Nalichnosti.find("id = ?1", maxDelivery.id ).firstResult(); // record with last 10360

        printDelivered(deliveredRecord);

        NalichnostiDto       nalichnostiDto = nalichnostiMapper.toDto(deliveredRecord);
        NalitemDto           nalitemDto0 =  nalichnostiDto.getNalitems().get(0);
        NalitemDto.SerNumDto serNumDto1 = nalitemDto0.getSerNums().get(1);

        serNumDto1.setSnumDesc("!!!!!!!");
        nalitemDto0.setPaytype("CAShe");
        nalichnostiDto.setStatus("OK2OK");
        nalichnostiDto.setIscheck(true);
        nalichnostiDto.setAvailDesc("AAAAAAAA");

        deliveredRecord = nalichnostiMapper.partialUpdate(nalichnostiDto, deliveredRecord);

        printDelivered(deliveredRecord);
    }

    @Inject
    NalitemMapper mapper;

    @Order(2)
    //@Disabled
    @Test
    @Transactional
    public void deliveryLinkDto() {
        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360// get last inventory record
        Nalichnosti deliveredRecord = Nalichnosti.find("id = ?1", maxDelivery.id ).firstResult(); // record with last 10360
        printDelivered(deliveredRecord);

        NalichnostiDto nalichnostiDto = nalichnostiMapper.toDto(deliveredRecord);

        NalitemDto nalitemDto = mapper.toDto( deliveredRecord.getNalitems().get(0) );
        nalitemDto.getSerNums().get(0).setSnumDesc("!!!!!!!");
        nalitemDto.getSerNums().get(0).setSnumDesc("!!!!!!!");
        nalitemDto.setPaytype("CAShe");

        Nalitem ni1 = mapper.partialUpdate(nalitemDto, deliveredRecord.getNalitems().get(0));
        //Nalitem ni1 = mapper.toEntity(nalitemDto);
        //ni1.setPaytype("Borko");

        String s1 = ni1.getPaytype();
        String s2 = deliveredRecord.getNalitems().get(0).paytype;
        Nalitem ni2 = deliveredRecord.getNalitems().get(0);

//        nalichnostiDto.setStatus("OK2OK");
//        nalichnostiDto.setIscheck(true);
//        nalichnostiDto.getNalitems().get(1).setIscheck(true);
//        nalichnostiDto.getNalitems().get(1).setPaytype("AAAAAAAA");

        printDelivered(deliveredRecord);

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


    @Inject
    NalichnostiMapper nalichnostiMapper;

    @Order(3)
    //@Disabled
    @Test
    //@Transactional
    public void deliveryToJsonDto() {

        Log.infof("********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
        // get last inventory record
        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360
        Nalichnosti lastResieve = Nalichnosti.find("firmaFid=?1 and id = ?2", maxDelivery.firmaFid, maxDelivery.id ).firstResult(); // record with last 10360
        NalichnostiDto nalichnostiDto = nalichnostiMapper.toDto(lastResieve);

        Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
        System.out.println( jsonb.toJson(nalichnostiDto));

        logStoreRecord(maxDelivery);
    }

    private static Nalichnosti getLastNalichnosti() {
        Query nativeQuery = getEntityManager().createNativeQuery("select max(id) from NALICHNOSTI");
        BigDecimal rId = (BigDecimal)nativeQuery.getSingleResult();
        Nalichnosti delivery = findById( rId.longValue());
        return delivery;
    }
}



