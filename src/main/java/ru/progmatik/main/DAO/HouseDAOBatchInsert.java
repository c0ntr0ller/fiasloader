package ru.progmatik.main.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.fias.House;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
/**
 *  компонент предназначен для занесения объектов типа House в базу данных
 */
@Component
public class HouseDAOBatchInsert {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${house_query:\"update or insert into house0 (houseid, aoguid, buildnum, houseguid, housenum, strstatus, postalcode, statstatus, eststatus) values(?, ?, ?, ?, ?, ?, ?, ?, ?)matching(houseid)\"}")
    private String house_query;

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;

    @Autowired
    DBService dbService;

    public void insertHouseArray(List<House> houseList, Connection connection) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(house_query)){
            connection.setAutoCommit(false);

            for (House house:houseList) {
                if (house.getAOGUID() != null && house.getHOUSEGUID() != null) {
                    statement.setString(1, house.getHOUSEID());
                    statement.setString(2, house.getAOGUID().toString());
                    statement.setString(3, house.getBUILDNUM());
                    statement.setString(4, house.getHOUSEGUID().toString());
                    statement.setString(5, house.getHOUSENUM());
                    statement.setBigDecimal(6, new BigDecimal(house.getSTRSTATUS()));
                    statement.setString(7, house.getPOSTALCODE());
                    statement.setBigDecimal(8, new BigDecimal(house.getSTATSTATUS()));
                    statement.setBigDecimal(9, new BigDecimal(house.getESTSTATUS()));

                    statement.addBatch();
                }
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
            logger.error("Error inserting Houses", e);
            e.printStackTrace();
        }
    }

}
