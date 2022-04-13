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
           logger.info("Parsing save file...");
           StringTokenizer stTables, stNatFields, stRelFields, stNatRelFields, stField;
           EdgeTable tempTable;
           EdgeField tempField;
           super.setCurrentLine(super.getBufferedReader().readLine()); //this should be "Table: "
           while (super.getCurrentLine().startsWith("Table: ")) {
               super.setNumFigure(Integer.parseInt(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1))); //get the Table number
               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be "{"
               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be "TableName"
               super.setTableName(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1));
               tempTable = new EdgeTable(super.getNumFigure() + super.DELIM + super.getTableName());

               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be the NativeFields list
               stNatFields = new StringTokenizer(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1), super.DELIM);
               super.setNumFields(stNatFields.countTokens());
               for (int i = 0; i < super.getNumFields(); i++) {
                   tempTable.addNativeField(Integer.parseInt(stNatFields.nextToken()));
               }

               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be the RelatedTables list
               stTables = new StringTokenizer(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1), super.DELIM);
               super.setNumTables(stTables.countTokens());
               for (int i = 0; i < super.getNumTables(); i++) {
                   tempTable.addRelatedTable(Integer.parseInt(stTables.nextToken()));
               }
               tempTable.makeArrays();

               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be the RelatedFields list
               stRelFields = new StringTokenizer(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1), super.DELIM);
               super.setNumFields(stRelFields.countTokens());

               for (int i = 0; i < super.getNumFields(); i++) {
                   tempTable.setRelatedField(i, Integer.parseInt(stRelFields.nextToken()));
               }

               super.getAlTables().add(tempTable);
               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be "}"
               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be "\n"
               super.setCurrentLine(super.getBufferedReader().readLine()); //this should be either the next "Table: ", #Fields#
           }
           String x;
           while ((x = super.getBufferedReader().readLine()) != null) {
               super.setCurrentLine(x);
               stField = new StringTokenizer(super.getCurrentLine(), super.DELIM);
               super.setNumFigure(Integer.parseInt(stField.nextToken()));
               super.setFieldName(stField.nextToken());
               tempField = new EdgeField(super.getNumFigure() + super.DELIM + super.getFieldName());
               tempField.setTableID(Integer.parseInt(stField.nextToken()));
               tempField.setTableBound(Integer.parseInt(stField.nextToken()));
               tempField.setFieldBound(Integer.parseInt(stField.nextToken()));
               tempField.setDataType(Integer.parseInt(stField.nextToken()));
               tempField.setVarcharValue(Integer.parseInt(stField.nextToken()));
               tempField.setIsPrimaryKey(Boolean.valueOf(stField.nextToken()).booleanValue());
               tempField.setDisallowNull(Boolean.valueOf(stField.nextToken()).booleanValue());
               if (stField.hasMoreTokens()) { //Default Value may not be defined
                   tempField.setDefaultValue(stField.nextToken());
               }
               super.getAlFields().add(tempField);
           }
           super.getBufferedReader().close();
           super.makeArrays();
       }
       catch(Exception e) {
           logger.debug(e.toString());
       }
    }
 

}