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
         logger.info("Parsing edge file...");
         String x;
         while ((x = super.getBufferedReader().readLine()) != null) {
            super.setCurrentLine(x);
            super.setCurrentLine(super.getCurrentLine().trim());
            if (super.getCurrentLine().startsWith("Figure ")) { //this is the start of a Figure entry
               super.setNumFigure(Integer.parseInt(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1))); //get the Figure number
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // this should be "{"
               super.setCurrentLine(super.getBufferedReader().readLine().trim());
               if (!super.getCurrentLine().startsWith("Style")) { // this is to weed out other Figures, like Labels
                  continue;
               } else {
                  super.setStyle(super.getCurrentLine().substring(super.getCurrentLine().indexOf("\"") + 1, super.getCurrentLine().lastIndexOf("\""))); //get the Style parameter
                  if (super.getStyle().startsWith("Relation")) { //presence of Relations implies lack of normalization
                     JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + super.getParseFile() + "\ncontains relations.  Please resolve them and try again.");
                     EdgeConvertGUI.setReadSuccess(false);
                     break;
                  }
                  if (super.getStyle().startsWith("Entity")) {
                     super.setIsEntity(true);
                  }
                  if (super.getStyle().startsWith("Attribute")) {
                     super.setIsAttribute(true);
                  }
                  if (!(super.getIsEntity() || super.getIsAttribute())) { //these are the only Figures we're interested in
                     continue;
                  }
                  super.setCurrentLine(super.getBufferedReader().readLine().trim()); //this should be Text
                  super.setText(super.getCurrentLine().substring(super.getCurrentLine().indexOf("\"") + 1, super.getCurrentLine().lastIndexOf("\"")).replaceAll(" ", "")); //get the Text parameter
                  if (super.getText().equals("")) {
                     JOptionPane.showMessageDialog(null, "There are entities or attributes with blank names in this diagram.\nPlease provide names for them and try again.");
                     EdgeConvertGUI.setReadSuccess(false);
                     break;
                  }
                  int escape = super.getText().indexOf("\\");
                  if (escape > 0) { //Edge denotes a line break as "\line", disregard anything after a backslash
                     super.setText(super.getText().substring(0, escape));
                  }

                  do { //advance to end of record, look for whether the text is underlined
                     super.setCurrentLine(super.getBufferedReader().readLine().trim());
                     if (super.getCurrentLine().startsWith("TypeUnderl")) {
                        super.setIsUnderlined(true);
                     }
                  } while (!super.getCurrentLine().equals("}")); // this is the end of a Figure entry

                  if (super.getIsEntity()) { //create a new EdgeTable object and add it to the alTables ArrayList
                     if (isTableDup(super.getText())) {
                        JOptionPane.showMessageDialog(null, "There are multiple tables called " + super.getText() + " in this diagram.\nPlease rename all but one of them and try again.");
                        EdgeConvertGUI.setReadSuccess(false);
                        break;
                     }
                     super.getAlTables().add(new EdgeTable(super.getNumFigure() + super.DELIM + super.getText()));
                  }
                  if (super.getIsAttribute()) { //create a new EdgeField object and add it to the alFields ArrayList
                     super.setTempField(new EdgeField(super.getNumFigure() + super.DELIM + super.getText()));
                     super.getTempField().setIsPrimaryKey(super.getIsUnderlined());
                     super.getAlFields().add(super.getTempField());
                  }
                  //reset flags
                  super.setIsEntity(false);
                  super.setIsAttribute(false);
                  super.setIsUnderlined(false);
               }
            } // if("Figure")
            if (super.getCurrentLine().startsWith("Connector ")) { //this is the start of a Connector entry
               super.setNumConnector(Integer.parseInt(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1))); //get the Connector number
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // this should be "{"
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // not interested in Style
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // Figure1
               super.setEndPoint1(Integer.parseInt(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1)));
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // Figure2
               super.setEndPoint2(Integer.parseInt(super.getCurrentLine().substring(super.getCurrentLine().indexOf(" ") + 1)));
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // not interested in EndPoint1
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // not interested in EndPoint2
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // not interested in SuppressEnd1
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // not interested in SuppressEnd2
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // End1
               super.setEndStyle1(super.getCurrentLine().substring(super.getCurrentLine().indexOf("\"") + 1, super.getCurrentLine().lastIndexOf("\""))); //get the End1 parameter
               super.setCurrentLine(super.getBufferedReader().readLine().trim()); // End2
               super.setEndStyle2(super.getCurrentLine().substring(super.getCurrentLine().indexOf("\"") + 1, super.getCurrentLine().lastIndexOf("\""))); //get the End2 parameter

               do { //advance to end of record
                  super.setCurrentLine(super.getBufferedReader().readLine().trim());
               } while (!super.getCurrentLine().equals("}")); // this is the end of a Connector entry

               super.getAlConnectors().add(new EdgeConnector(super.getNumConnector() + DELIM + super.getEndPoint1() + DELIM + super.getEndPoint2() + DELIM + super.getEndStyle1() + DELIM + super.getEndStyle2()));
            } // if("Connector")
         } // while()

         super.getBufferedReader().close();
         super.makeArrays(); //convert ArrayList objects into arrays of the appropriate Class type
         super.resolveConnectors(); //Identify nature of Connector endpoints
      } catch (Exception e) {
         logger.debug(e.toString());
      }
    }

    public boolean isTableDup(String testTableName) {`
        //TODO: Check if duplicate table
           for (int i = 0; i < super.getAlTables().size(); i++) {
         EdgeTable tempTable = (EdgeTable)super.getAlTables().get(i);
         if (tempTable.getName().equals(testTableName)) {
            logger.warn("Test table " + testTableName + " is a duplicate.");
            return true;
         }
      }
      return false;
    }
}