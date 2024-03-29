package bg.comsoft.test;

import bg.comsoft.data.entity.*;
import bg.comsoft.data.mapper.NalichnostiDto;
import bg.comsoft.data.mapper.NalichnostiDto.NalitemDto;
import bg.comsoft.data.mapper.NalichnostiMapper;
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
        Nalichnosti lastDeliveryRecord = Nalichnosti.find("firmaFid=?1 and id = ?2", maxDelivery.firmaFid, maxDelivery.id ).firstResult(); // record with last 10360
        NalichnostiDto nalichnostiDto = nalichnostiMapper.toDto(lastDeliveryRecord);

        List<NalitemDto> deliveryItems = nalichnostiDto.getNalitems();


      Log.infof("\n Delivery id: %s, dateIn: %s, karta: %s \n Firma Id: %s, fname: %s",
              nalichnostiDto.getId(), nalichnostiDto.getDateIn(), nalichnostiDto.getKarta(), nalichnostiDto.getFirmaFid().getId(),  nalichnostiDto.getFirmaFid().getFname());

        for(NalitemDto nalitem :  deliveryItems) {
            NalitemDto.ProduktiDto prod = nalitem.getProd();
            Log.infof("%-15s, %-70.70s, %6s,  %4s, %8s", nalitem.getProd().getPartNumber(), prod.getStshort(),  nalitem.getQty(),  nalitem.getWorm(), nalitem.getPricein());

            for (NalitemDto.SerNumDto serNum : nalitem.getSerNums()) {
               Log.infof("| %-10s | %10s |", serNum.getSernum(), serNum.getSnumDesc());
            }
        }
    }


    @Order(2)
    //@Disabled
    @Test
    @Transactional
    public void deliveryUpdateDto() {

        Log.infof("\n ********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
        // get last inventory record

        Nalichnosti maxDelivery = getLastNalichnosti();  // 10360

        Nalichnosti deliveredRecord = Nalichnosti.find("firmaFid=?1 and id = ?2", maxDelivery.firmaFid, maxDelivery.id ).firstResult(); // record with last 10360

        printDelivered(deliveredRecord);

        NalichnostiDto nalichnostiDto = nalichnostiMapper.toDto(deliveredRecord);

        nalichnostiDto.setStatus("OK2OK");
        nalichnostiDto.setIscheck(true);
        nalichnostiDto.getNalitems().get(3).setIscheck(true);
        nalichnostiDto.getNalitems().get(3).setPaytype("AAA");
        Log.infof("Setting ", nalichnostiDto.getNalitems().get(3).getId(), nalichnostiDto.getNalitems().get(3) );
        deliveredRecord = nalichnostiMapper.partialUpdate(nalichnostiDto, deliveredRecord);

        printDelivered(deliveredRecord);


/*

        maxDelivery.karta = "10360";
        maxDelivery.status = "OK";
        maxDelivery.firmaFid.fpbox = "111";
        Nalitem nalitem = maxDelivery.nalitems.get(1);
        nalitem.paytype="Invoice1";
//        nalitem.serNums.get(1).snumDesc = "@";
//        nalitem.serNums.get(2).snumDesc = "@!";
        nalitem.serNums.get(1).snumDesc = "10360";
        nalitem.serNums.get(2).snumDesc = "10360";
        nalitem.prod.stdesc="--";
*/


        logStoreRecord(maxDelivery);
    }

    private static void printDelivered(Nalichnosti deliveredRecord) {
        logStoreRecord(deliveredRecord);
        Log.infof("==> %s %s %s", deliveredRecord.id, deliveredRecord.status, deliveredRecord.ischeck);
        for( Nalitem nalitem : deliveredRecord.getNalitems() ) {
            Log.infof("===> %s %s %s", nalitem.id, deliveredRecord.id, nalitem.ischeck, nalitem.paytype);
            Produkti prod = nalitem.prod;
            Log.infof( "%-15s, %-70.70s, %6s,  %4s, %8s", nalitem.prod.partNumber, prod.stshort, nalitem.qty, nalitem.worm, nalitem.pricein);
            for (SerNum serNum : nalitem.serNums) {
                Log.infof("| %-10s | %-10s | %10s |", serNum.id, serNum.sernum, serNum.snumDesc);
            }
        }
    }

    private static void logStoreRecord(Nalichnosti maxDelivery) {
        Log.infof("\n Delivery id: %s, dateIn: %s, karta: %s \n Firma Id: %s, fname: %s", maxDelivery.id, maxDelivery.dateIn, maxDelivery.karta, maxDelivery.firmaFid.id,  maxDelivery.firmaFid.fname);
    }


    @Inject
    NalichnostiMapper nalichnostiMapper;

    @Order(3)
    //@Disabled
    @Test
    //@Transactional
    public void deliveryToJsonDto() {

        Log.infof("\n ********* Deliveries %s, Firms %s  *************", count(), Firmi.count());
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



