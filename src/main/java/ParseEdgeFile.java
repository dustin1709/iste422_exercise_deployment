import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseEdgeFile extends EdgeConvertFileParser {

    private static Logger logger = LogManager.getLogger(ParseEdgeFile.class.getName());

   public ParseEdgeFile(File constructorFile) {
      super(constructorFile);
      
      try {

        logger.info("Parsing Edge File...");

        String x;

        while ((x = super.getBufferedReader().readLine()) != null) {

            super.setCurrentLine(x);
            super.setCurrentLine(super.getCurrentLine().trim());

            //TODO: Check current line

        }

        super.getBufferedReader().close();
        super.makeArrays();
        super.resolveConnectors();
      } catch (Exception e) {
        logger.debug(e.toString());
      }
    }

    private boolean isTableDup(String testTableName) {
        //TODO: Check if duplicate table
    }
}