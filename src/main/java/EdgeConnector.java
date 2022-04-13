import java.util.StringTokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeConnector {
   public static Logger logger = LogManager.getLogger(EdgeConnector.class.getName());

   private int numConnector, endPoint1, endPoint2;
   private String endStyle1, endStyle2;
   private boolean isEP1Field, isEP2Field, isEP1Table, isEP2Table;
      
   public EdgeConnector(String inputString) {
      logger.debug(String.format("Connecting to Edge File: " + inputString));
      
      StringTokenizer st = new StringTokenizer(inputString, EdgeConvertFileParser.DELIM);
      numConnector = Integer.parseInt(st.nextToken());
      endPoint1 = Integer.parseInt(st.nextToken());
      endPoint2 = Integer.parseInt(st.nextToken());
      endStyle1 = st.nextToken();
      endStyle2 = st.nextToken();
      isEP1Field = false;
      isEP2Field = false;
      isEP1Table = false;
      isEP2Table = false;
   }
   
   public int getNumConnector() {
      return numConnector;
   }
   
   public int getEndPoint1() {
      return endPoint1;
   }
   
   public int getEndPoint2() {
      return endPoint2;
   }
   
   public String getEndStyle1() {
      return endStyle1;
   }
   
   public String getEndStyle2() {
      return endStyle2;
   }
   public boolean getIsEP1Field() {
      return isEP1Field;
   }
   
   public boolean getIsEP2Field() {
      return isEP2Field;
   }

   public boolean getIsEP1Table() {
      return isEP1Table;
   }

   public boolean getIsEP2Table() {
      return isEP2Table;
   }

   public void setIsEP1Field(boolean value) {
      logger.info(String.format("Setting EP1 Field: " + value));
      logger.debug(String.format("EP1 Field is now: " + value));
      isEP1Field = value;
   }
   
   public void setIsEP2Field(boolean value) {
      logger.info(String.format("Setting EP2 Field: " + value));
      logger.debug(String.format("EP2 Field is now: " + value));
      isEP2Field = value;
   }

   public void setIsEP1Table(boolean value) {
      logger.info(String.format("Setting EP1 Table: " + value));
      logger.debug(String.format("EP1 Table is now: " + value));
      isEP1Table = value;
   }

   public void setIsEP2Table(boolean value) {
      logger.info(String.format("Setting EP2 Table: " + value));
      logger.debug(String.format("EP2 Table is now: " + value));
      isEP2Table = value;
   }
}
