import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

public class EdgeConvertCreateDDLTest {
    
	public static Logger logger = LogManager.getLogger(EdgeConvertCreateDDLTest.class.getName());
    EdgeTable tables[] = new EdgeTable[4]; //master copy of EdgeTable objects
    EdgeField fields[] = new EdgeField[4]; //master copy of EdgeField objects

	ConcreteDDL testObj;

	@Before
	public void setUp() throws Exception {
		logger.debug(String.format("Setting up Test Edge Convert Create DDL."));
		testObj = new ConcreteDDL(tables, fields);
        testObj.initialize();
	}

    // @Test
    // public void testGetTable() {
    //     assertTrue(testObj.getTable(4) instanceof EdgeTable);
        
    // }

    // @Test
    // public void testGetFields() {
    //     assertTrue(testObj.getField(4) instanceof EdgeField);
    // }

}
