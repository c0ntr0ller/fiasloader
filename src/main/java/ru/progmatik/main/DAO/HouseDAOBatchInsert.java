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

    private static final String HOUSE_INSERT_QUERY = "update or insert into house0 (houseid, aoguid, buildnum, houseguid, housenum, strstatus) " +
            "values(?, ?, ?, ?, ?, ?)" +
            "matching(houseid)";

    @Value("${batchsize:1000}")
    private int BATCH_SIZE;

    @Autowired
    DBService dbService;

    public void insertHouseArray(List<House> houseList, Connection connection) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int count = 0;

        try(PreparedStatement statement = connection.prepareStatement(HOUSE_INSERT_QUERY)){
            connection.setAutoCommit(false);

            for (House house:houseList) {
                statement.setString(1, house.getHOUSEID());
                statement.setString(2, house.getAOGUID());
                statement.setString(3, house.getBUILDNUM());
                statement.setString(4, house.getHOUSEGUID());
                statement.setString(5, house.getHOUSENUM());
                statement.setBigDecimal(6, new BigDecimal(house.getSTRSTATUS()));

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
            logger.error("Error inserting Houses", e);
            e.printStackTrace();
        }
    }

}
