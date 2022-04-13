import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseSaveFile extends EdgeConvertFileParser {

    private static Logger logger = LogManager.getLogger(ParseEdgeFile.class.getName());

    public ParseSaveFile(File constructorFile) {
        super(constructorFile);
        try {

            logger.info("Parsing Save File...");

            StringTokenizer stTables, stNatFields, stRelFields, stNatRelFields, stField;
            EdgeTable tempTable;
            EdgeField tempField;

            super.setCurrentLine(super.getBufferedReader().readLine());
            super.setCurrentLine(super.getBufferedReader().readLine());

            while (super.getCurrentLine().startsWith("Table: ")) {
                //TODO: Set current line data
            }
            
            String x;
            
            while ((x = super.getBufferedReader().readLine()) != null) {
                //TODO: Set current line data
            }

            super.getBufferedReader().close();
            this.makeArrays();
        }
        catch(Exception e) {
            logger.debug(e.toString());
        }
    }
 

}