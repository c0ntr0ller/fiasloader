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

    private static final String ADDROBJ_INSERT_QUERY = "insert into addrobj (aoid, aoguid, currstatus, livestatus, offname, parentguid, regioncode, shortname, startdate) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                statement.setString(1, addrobj.getAOID());
                statement.setString(2, addrobj.getAOGUID());
                statement.setBigDecimal(3, new BigDecimal(addrobj.getCURRSTATUS()));
                statement.setByte(4, addrobj.getLIVESTATUS());
                statement.setString(5, addrobj.getOFFNAME());
                statement.setString(6, addrobj.getPARENTGUID());
                statement.setString(7, addrobj.getREGIONCODE());
                statement.setString(8, addrobj.getSHORTNAME());
                statement.setTimestamp(9, new Timestamp(addrobj.getSTARTDATE().toGregorianCalendar().getTimeInMillis()));

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
