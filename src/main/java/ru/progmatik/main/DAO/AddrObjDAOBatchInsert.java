package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.fias.Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class AddrObjDAOBatchInsert {
    private static Logger log = LoggerFactory.getLogger(AddrObjDAOBatchInsert.class);

    private static final String ADDROBJ_INSERT_QUERY = "  insert into addrobj(" +
            "actstatus," +
            "aoguid," +
            "aoid," +
            "aolevel," +
            "areacode," +
            "autocode," +
            "centstatus," +
            "citycode," +
            "code," +
            "currstatus," +
            "enddate," +
            "formalname," +
            "ifnsfl," +
            "ifnsul," +
            "nextid," +
            "offname," +
            "okato," +
            "oktmo," +
            "operstatus," +
            "parentguid," +
            "placecode," +
            "plaincode," +
            "postalcode," +
            "previd," +
            "regioncode," +
            "shortname," +
            "startdate," +
            "streetcode," +
            "terrifnsfl," +
            "terrifnsul," +
            "updatedate," +
            "ctarcode," +
            "extrcode," +
            "sextcode," +
            "livestatus," +
            "normdoc)" +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;

    @Autowired
    DBService dbService;

    public void insertAddrObjArray(List<Object> objectList){
        int count = 0;

        try(Connection connection = dbService.getConnection();
                PreparedStatement statement = connection.prepareStatement(ADDROBJ_INSERT_QUERY)){
            connection.setAutoCommit(false);

            for (Object addrobj:objectList) {

                statement.setBigDecimal(1, new BigDecimal(addrobj.getACTSTATUS()));
                statement.setString(2, addrobj.getAOGUID());
                statement.setString(3, addrobj.getAOID());
                statement.setBigDecimal(4, new BigDecimal(addrobj.getAOLEVEL()));
                statement.setString(5, addrobj.getAREACODE());
                statement.setString(6, addrobj.getAUTOCODE());
                statement.setBigDecimal(7, new BigDecimal(addrobj.getCENTSTATUS()));
                statement.setString(8, addrobj.getCITYCODE());
                statement.setString(9, addrobj.getCODE());
                statement.setBigDecimal(10, new BigDecimal(addrobj.getCURRSTATUS()));
                statement.setTimestamp(11, new Timestamp(addrobj.getENDDATE().toGregorianCalendar().getTimeInMillis()));
                statement.setString(12, addrobj.getFORMALNAME());
                statement.setString(13, addrobj.getIFNSFL());
                statement.setString(14, addrobj.getIFNSUL());
                statement.setString(15, addrobj.getNEXTID());
                statement.setString(16, addrobj.getOFFNAME());
                statement.setString(17, addrobj.getOKATO());
                statement.setString(18, addrobj.getOKTMO());
                statement.setBigDecimal(19, new BigDecimal(addrobj.getOPERSTATUS()));
                statement.setString(20, addrobj.getPARENTGUID());
                statement.setString(21, addrobj.getPLACECODE());
                statement.setString(22, addrobj.getPLAINCODE());
                statement.setString(23, addrobj.getPOSTALCODE());
                statement.setString(24, addrobj.getPREVID());
                statement.setString(25, addrobj.getREGIONCODE());
                statement.setString(26, addrobj.getSHORTNAME());
                statement.setTimestamp(27, new Timestamp(addrobj.getSTARTDATE().toGregorianCalendar().getTimeInMillis()));
                statement.setString(28, addrobj.getSTREETCODE());
                statement.setString(29, addrobj.getTERRIFNSFL());
                statement.setString(30, addrobj.getTERRIFNSUL());
                statement.setTimestamp(31, new Timestamp(addrobj.getUPDATEDATE().toGregorianCalendar().getTimeInMillis()));
                statement.setString(32, addrobj.getCTARCODE());
                statement.setString(33, addrobj.getEXTRCODE());
                statement.setString(34, addrobj.getSEXTCODE());
                statement.setBigDecimal(35, new BigDecimal(addrobj.getLIVESTATUS()));
                statement.setString(36, addrobj.getNORMDOC());
//                statement.setString(1, addrobj.getAOID());
//                statement.setString(2, addrobj.getAOGUID());
//                statement.setBigDecimal(3, new BigDecimal(addrobj.getCURRSTATUS()));
//                statement.setByte(4, addrobj.getLIVESTATUS());
//                statement.setString(5, addrobj.getOFFNAME());
//                statement.setString(6, addrobj.getPARENTGUID());
//                statement.setString(7, addrobj.getREGIONCODE());
//                statement.setString(8, addrobj.getSHORTNAME());
//                statement.setTimestamp(9, new Timestamp(addrobj.getSTARTDATE().toGregorianCalendar().getTimeInMillis()));

                statement.addBatch();

                count++;

                if (count%BATCH_SIZE == 0) {
                    statement.executeBatch();
                    connection.commit();
                    count = 0;
                }
            }

            if(count > 0){
                statement.executeBatch();
                connection.commit();
            }
        } catch ( SQLException e) {
            log.error("Error inserting Object", e);
            e.printStackTrace();
        }
    }
}
