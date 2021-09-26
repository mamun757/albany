package selenium_basic_test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestngTest {
    private static final Logger LOGGER = LogManager.getLogger(TestngTest.class);

    @Test
    public void run(){
        ResultSet resultSet = SqlConnector.readData("select * from monthly_mortgage");

       try {
           while (resultSet.next()){
               System.out.println("The Home price is: " + resultSet.getString("homevalue"));
               System.out.println("The Downpayment is: " + resultSet.getString("downpayment"));
               System.out.println("The Loan Amount is: " + resultSet.getString("loanamount"));
           }

       }catch (SQLException e){
           LOGGER.error("sql exception :" + e.getMessage());
       }
    }
}
