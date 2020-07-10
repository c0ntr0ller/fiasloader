package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.fias.Object;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 *  компонент предназначен для занесения объектов типа AddrObj в базу данных
 */
@Component
public class AddrObjDAOBatchInsert {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${addrobj_query:\"update or insert into addrobj(aoguid,aoid,aolevel,citycode,currstatus,formalname,nextid,offname,operstatus,parentguid,placecode,previd,regioncode,shortname,streetcode,livestatus, plaincode, div_type)values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) matching(aoid)\"}")
    private String addrobj_query;

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;



    public void insertAddrObjArray(List<Object> objectList, Connection connection) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(addrobj_query)){

            connection.setAutoCommit(false);

            for (Object addrobj:objectList) {
                statement.setString(1, addrobj.getAOGUID());
                statement.setString(2, addrobj.getAOID());
                statement.setBigDecimal(3, new BigDecimal(addrobj.getAOLEVEL()));
                statement.setString(4, addrobj.getCITYCODE());
                statement.setBigDecimal(5, new BigDecimal(addrobj.getCURRSTATUS()));
                statement.setString(6, addrobj.getFORMALNAME());
                statement.setString(7, addrobj.getNEXTID());
                statement.setString(8, addrobj.getOFFNAME());
                statement.setBigDecimal(9, new BigDecimal(addrobj.getOPERSTATUS()));
                statement.setString(10, addrobj.getPARENTGUID());
                statement.setString(11, addrobj.getPLACECODE());
                statement.setString(12, addrobj.getPREVID());
                statement.setString(13, addrobj.getREGIONCODE());
                statement.setString(14, addrobj.getSHORTNAME());
                statement.setString(15, addrobj.getSTREETCODE());
                statement.setBigDecimal(16, new BigDecimal(addrobj.getLIVESTATUS()));
                statement.setString(17, addrobj.getPLAINCODE());
                statement.setBigDecimal(18, new BigDecimal(addrobj.getDIVTYPE()));
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
            logger.error("Error inserting Object", e);
            e.printStackTrace();
        }
    }
}
