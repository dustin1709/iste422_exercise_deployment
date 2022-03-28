import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

public class EdgeConnectorTest {

	public static Logger logger = LogManager.getLogger(EdgeConnectorTest.class.getName());

	EdgeConnector testObj;

	@Before
	public void setUp() throws Exception {
		logger.debug(String.format("Setting up Test Edge Connector."));
		testObj = new EdgeConnector("1|2|3|testStyle1|testStyle2");
	}

	@Test
	public void testGetNumConnector() {
		// Example of how a value can be passed into a test
		String opt1Str = System.getProperty("optionone");
		final long opt1;
		if (opt1Str == null) {
			logger.warn(String.format("No Number Connector Value"));
			opt1 = 1;
		}
		else {
			opt1 = Long.parseLong(opt1Str);
		}
		assertEquals("numConnector was intialized to 1 so it should be 1",(long)opt1,testObj.getNumConnector());
		assertTrue(Integer.valueOf(testObj.getNumConnector()) instanceof Integer);
	}

	@Test
	public void testGetEndPoint1() {
		assertEquals("EndPoint1 was intialized to 2",2,testObj.getEndPoint1());
		assertTrue(Integer.valueOf(testObj.getEndPoint1()) instanceof Integer);
	}

	@Test
	public void testGetEndPoint2() {
		assertEquals("EndPoint2 was intialized as 3",3,testObj.getEndPoint2());
		assertTrue(Integer.valueOf(testObj.getEndPoint2()) instanceof Integer);
	}

	@Test
	public void testGetEndStyle1() {
		assertEquals("endStyle1 was intialized to \"testStyle1\"","testStyle1",testObj.getEndStyle1());
		assertTrue(testObj.getEndStyle1() instanceof String);
	}

	@Test
	public void testGetEndStyle2() {
		assertEquals("endStyle1 was intialized to \"testStyle1\"","testStyle2",testObj.getEndStyle2());
		assertTrue(testObj.getEndStyle2() instanceof String);
	}

	@Test
	public void testGetIsEP1Field() {
		assertEquals("isEP1Field should be false",false,testObj.getIsEP1Field());
		assertTrue(Boolean.valueOf(testObj.getIsEP1Field()) instanceof Boolean);
	}

	@Test
	public void testGetIsEP2Field() {
		assertEquals("IsEP2Field should be false",false,testObj.getIsEP2Field());
		assertTrue(Boolean.valueOf(testObj.getIsEP2Field()) instanceof Boolean);
	}

	@Test
	public void testGetIsEP1Table() {
		assertEquals("isEP1Table should be false",false,testObj.getIsEP1Table());
		assertTrue(Boolean.valueOf(testObj.getIsEP1Table()) instanceof Boolean);
	}

	@Test
	public void testGetIsEP2Table() {
		assertEquals("isEP2Table should be false",false,testObj.getIsEP2Table());
		assertTrue(Boolean.valueOf(testObj.getIsEP2Table()) instanceof Boolean);
	}

	@Test
	public void testSetIsEP1Field() {
		logger.info(String.format("Setting EP1 Table: false"));
      	logger.debug(String.format("EP1 Table is now: false"));
		testObj.setIsEP1Field(false);
		assertEquals("isEP1Field should be what you set it to",false,testObj.getIsEP1Field());
		assertTrue(Boolean.valueOf(testObj.getIsEP1Field()) instanceof Boolean);
	}

	@Test
	public void testSetIsEP2Field() {
		logger.info(String.format("Setting EP2 Field: false"));
      	logger.debug(String.format("EP2 Field is now: false"));
		testObj.setIsEP2Field(false);
		assertEquals("isEP2Field should be what you set it to",false,testObj.getIsEP2Field());
		assertTrue(Boolean.valueOf(testObj.getIsEP2Field()) instanceof Boolean);
	}

	@Test
	public void testSetIsEP1Table() {
		logger.info(String.format("Setting EP1 Table: false"));
      	logger.debug(String.format("EP1 Table is now: false"));
		testObj.setIsEP1Table(false);
		assertEquals("isEp1Table should be what you set it to",false,testObj.getIsEP1Table());
		assertTrue(Boolean.valueOf(testObj.getIsEP1Table()) instanceof Boolean);
	}

	@Test
	public void testSetIsEP2Table() {
		logger.info(String.format("Setting EP2 Table: false"));
      	logger.debug(String.format("EP2 Table is now: false"));
		testObj.setIsEP2Table(false);
		assertEquals("isEp2Table should be what you set it to",false,testObj.getIsEP2Table());
		assertTrue(Boolean.valueOf(testObj.getIsEP2Table()) instanceof Boolean);
	}

}
