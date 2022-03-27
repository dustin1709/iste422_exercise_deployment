import static org.junit.Assert.*;
import java.util.List;
import java.util.Map.Entry;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class EdgeFieldTest {
    EdgeField edgeField;

    @Before
    public void createEdgeField() {
        edgeField = new EdgeField("1|helloworld");
    }

    @Test
    public void getNumFigureTest() {
        int value = edgeField.getNumFigure();
        assertEquals("edgeField's numFigure should be", 1, value);
    }

    public void getNameTest() {
        String value = edgeField.getName();
        assertEquals("edgeField's getName should return", "helloworld", value);
    }

    public void getTableIDTest() {
        int value = edgeField.getTableID();
        assertEquals("edgeField's getTableID should return", 0, value);
    }

    public void setTableIDTest() {
        edgeField.setTableID(2);
        int value = edgeField.getTableID();
        assertEquals("edgeField's new tableID for setTableID should be", 2, value);
    }

    public void getTableBoundTest() {
        int value = edgeField.getTableBound();
        assertEquals("edgeField's getTableBound should return", 0, value);
    }

    public void setTableBoundTest() {
        edgeField.setTableBound(2);
        int value = edgeField.getTableBound();
        assertEquals("edgeField's new tableBound for setTableBound should be", 2, value);
    }

    public void getFieldBoundTest() {
        int value = edgeField.getFieldBound();
        assertEquals("edgeField's getFieldBound should return", 0, value);
    }

    public void setFieldBoundTest() {
        edgeField.setFieldBound(2);
        int value = edgeField.getFieldBound();
        assertEquals("edgeField's new fieldBound for setFieldBound should be", 2, value);
    }

    public void getDisallowNullTest() {
        boolean value = edgeField.getDisallowNull();
        assertEquals("edgeField's disallowNull should return", false, value);
    }

    public void setDisallowNullTest() {
        edgeField.setDisallowNull(true);
        boolean value = edgeField.getDisallowNull();
        assertEquals("edgeField's new disallowNull for setDisallowNull should be", true, value);
    }  
    
    public void getIsPrimaryKeyTest() {
        boolean value = edgeField.getIsPrimaryKey();
        assertEquals("edgeField's getIsPrimaryKey should return", false, value);
    }

    public void setIsPrimaryKeyTest() {
        edgeField.setIsPrimaryKey(true);
        boolean value = edgeField.getIsPrimaryKey();
        assertEquals("edgeField's new isPrimaryKey for setIsPrimaryKey should be", true, value);
    }

    public void getDefaultValueTest() {
        String value = edgeField.getDefaultValue();
        assertEquals("edgeField's getDefaultValue should return", "", value);
    }

    public void setDefaultValueTest() {
        edgeField.setDefaultValue("hey");
        String value = edgeField.getDefaultValue();
        assertEquals("edgeField's defaultValue for setDefaultValue should return", "hey", value);
    }

    public void getVarcharValueTest() {
        int value = edgeField.getVarcharValue();
        assertEquals("edgeField's getVarcharValue should return", 1, value);
    }

    public void setVarcharValueTest() {
        edgeField.setVarcharValue(2);
        int value = edgeField.getVarcharValue();
        assertEquals("edgeField's varcharValue for setVarcharValue should result in", 2, value);
    }

    public void getDataTypeTest() {
        int value = edgeField.getDataType();
        assertEquals("edgeField's getDataType should result", 0, value);
    }

    public void setDataTypeTest() {
        edgeField.setDataType(3);
        int value = edgeField.getDataType();
        assertEquals("edgeField's dataType for setDataType should be", 3, value);
    }

    public static void getStrDataTypeTest() {
        String[] value = EdgeField.getStrDataType();
        assertArrayEquals("edgeField's getStrDataType should result", new String[] {"Varchar", "Boolean", "Integer", "Double"}, value);
    }

    public void setVarcharValueTest_WhenLessThanZero() {
        edgeField.setVarcharValue(-1);
        int value = edgeField.getVarcharValue();
        assertEquals("edgeField's varcharValue WhenLessThanZero for setVarcharValue should result in", 0, value);
    }

    public void setDataTypeTest_WhenGreaterThanFour() {
        edgeField.setDataType(5);
        int value = edgeField.getDataType();
        assertEquals("edgeField's dataType WhenGreaterThanFour for setDataType should be", 0, value);
    }

    public void setDataTypeTest_WhenEqualsFour() {
        edgeField.setDataType(4);
        int value = edgeField.getDataType();
        assertEquals("edgeField's dataType WhenEqualsFour for setDataType should be", 0, value);
    }
}
