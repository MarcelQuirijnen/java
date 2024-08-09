/*********************/
/* Write a java program that connects to a database using standard JDBC  */
/* and extracts all the records with a timestamp that falls within       */
/* May 1, 2017 00:00:00 and May 1, 2017 23:59:59 GMT time into a CSV     */
/* file named /tmp/data.csv in the same order as the columns listed      */
/* in the table.                                                         */
/* You can expect the number of rows retrieved from the database to be   */
/* around 50 million.                                                    */
/*********************/
/* Given this :
/* CREATE TABLE location (
/*    advertiser_id varchar(255) NOT NULL,
/*    latitude double NOT NULL,
/*    longitude double NOT NULL,
/*    horizontal_accuracy double NOT NULL,
/*    timestamp datetime(6) NOT NULL,
/* PRIMARY KEY(advertiser_id),
/* ) ENGINE=InnoDB
/*********************/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.sql.SQLException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import mycsv;


public class ProcessBigTable {
  
  private static final String dbClassName = "com.mysql.jdbc.Driver";
  private static final String CONNECTION = "jdbc:mysql://localhost:3306/xmodesocial";
  private static Connection con = null;
  private static PreparedStatement pst = null;
  private static ResultSet rs = null;
  private static FileWriter writer = null;
  private static String fileName = "/tmp/data.csv";
  
  
  public static void main(String args[]) throws Exception {
    try {
       Class.forName(dbClassName);
       Properties props = new Properties();
       props.setProperty("user", "xmodesocial");
       props.setProperty("password", "xmodesocial");
       props.setProperty("useSSL", "false");
       props.setProperty("autoReconnect", "true");
       con = DriverManager.getConnection(CONNECTION, props);
    } catch (Exception e) {
       //System.out.println(e);
       e.printStackTrace();
    }

    try {
       // select data as stored, since table does not have TZ info, I assume timestamp is in GMT format
       // timestamp data converted by CONVERT_TZ('2017-10-15 12:00:00','from_MET','to_GMT')
       // Should use UTC instead of GMT. There's no time difference, but GMT is a timezone subject to DST while UTC is a time standard worldwide
       // read few rows at a time (aka streaming) to prevent choking and running out of heap space
       pst = con.prepareStatement("SELECT * FROM xmodesocial.location WHERE timestamp BETWEEN '2017-05-01 00:00:00' AND '2017-05-01 23:59:59'",
                                   ResultSet.TYPE_FORWARD_ONLY,
                                   ResultSet.CONCUR_READ_ONLY
                                 );
       pst.setFetchSize(Integer.MIN_VALUE);
       rs = pst.executeQuery();
       
       writer = new FileWriter(fileName, true);
       // don't write csv header line when file exists
       boolean alreadyExists = new File(fileName).exists();
       if (!alreadyExists) {
          writer.write("Advertiser Id, Latitude, Longitude, Horizontal Accuracy, Timestamp" + System.lineSeparator());
       }
       
       while(rs.next()) {
          //System.out.println(rs.getString("advertiser_id"));
          String str = rs.getString("advertiser_id") + ',' + rs.getString("latitude") + ',' + rs.getString("longitude") + ',' + rs.getString("horizontal_accuracy") + ',' + rs.getString("timestamp");
          writer.write(str + System.lineSeparator());
       }

    } catch (Exception e ) {
       e.printStackTrace();
    } finally {
       writer.flush();  // not needed when closing, but this is java
       writer.close();
       if (pst != null) { pst.close(); }
    }
  }
}
