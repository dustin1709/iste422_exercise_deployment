import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {
    
    public static Logger logger = LogManager.getLogger(EdgeTableTest.class.getName());

    EdgeTable testObj;

    @Before
    public void setUp() throws Exception {
        logger.debug(String.format("Setting up Test Edge Table."));
        testObj = new EdgeTable("1|testTable1");
    }

    @Test
    public void testGetNumFigure() {
        assertEquals("NumFigure was initialized to 1",1,testObj.getNumFigure());
        assertTrue(Integer.valueOf(testObj.getNumFigure()) instanceof Integer);
    }

    @Test
    public void testGetName() {
        assertEquals("Name was initialized to \"testTable1\"","testTable1",testObj.getName());
        assertTrue(testObj.getName() instanceof String);
    }

    @Test
    public void testGetRelatedTables() {
        testObj.makeArrays();
        assertEquals("RelatedTablesArray was initialized to [] so length should be 0", 0, testObj.getRelatedTablesArray().length);
    }

    @Test
    public void testSetGetRelatedTables() {
        // first we set and then we test
        testObj.addRelatedTable(1);
        testObj.makeArrays();
        assertEquals("RelatedTablesArray was set to [1]", 1, testObj.getRelatedTablesArray()[0]);
        assertEquals("RelatedTablesArray length should be 1", 1, testObj.getRelatedTablesArray().length);
    }

    @Test
    public void testGetRelatedFields() {
        testObj.addNativeField(1);
        testObj.makeArrays();
        assertEquals("RelatedFieldsArray was initialized to [1] so length should be 1", 1, testObj.getRelatedFieldsArray().length);
    }

    @Test
    public void testSetGetRelatedFields() {
        // first we set and then we test
        testObj.addNativeField(1);
        testObj.makeArrays();
        testObj.setRelatedField(0, 1);
        assertEquals("RelatedFieldsArray was set to [1]", 1, testObj.getRelatedFieldsArray()[0]);
        assertEquals("RelatedFieldsArray length should be 1", 1, testObj.getRelatedFieldsArray().length);
    }

    @Test
    public void testGetNativeFields() {
        testObj.makeArrays();
        assertEquals("NativeFieldsArray was initialized to [] so length should be 0", 0, testObj.getNativeFieldsArray().length);
    }

    @Test
    public void testSetGetNativeFields() {
        // first we set and then we test
        testObj.addNativeField(1);
        testObj.makeArrays();
        assertEquals("NativeFieldsArray was set to [1]", 1, testObj.getNativeFieldsArray()[0]);
        assertEquals("NativeFieldsArray length should be 1", 1, testObj.getNativeFieldsArray().length);
    }

    @Test
    public void testToString() {
        testObj.makeArrays();
        System.out.println(testObj.toString());
        assertTrue(testObj.toString() instanceof String);

    }

    @Test(expected = NoSuchElementException.class)
    public void testConstructorNoInput() {
        testObj = new EdgeTable("");
    }

    @Test(expected = NumberFormatException.class)
    public void testConstructorWrongDelimiter() {
        testObj = new EdgeTable("1,testTable1");
    }

    @Test(expected = NumberFormatException.class)
    public void testConstructorWrongParams() {
        testObj = new EdgeTable("hello|10");
    }

}
