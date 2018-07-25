package org.asite.automation.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.asite.automation.common.base.TestBaseDataCheck;
import org.asite.automation.common.logger.AutomationLogger;
import org.asite.automation.common.resources.AdoddleCommonStringPool;
import org.asite.automation.common.utils.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cucumber.api.Scenario;

// TODO: Auto-generated Javadoc
/**
 * The Class TestdataCheckUtils.
 * @author jasminprajapati
 */
public class TestdataCheckUtils implements TestBaseDataCheck {

	/** The scenario list. */
	private static List<String> 		scenarioList 		= new ArrayList<String>();
	
	public static Map<String, String>	collectionDataMap 	= null;
	
	public static Logger				log					= AutomationLogger.getInstance().getLogger(TestdataCheckUtils.class);
	
	/**
	 * Checks if TestData are available
	 *
	 * @return flag
	 */
	public boolean isTestDataAvailable(Scenario s) {
		scenarioList = parseDataCheckScenariosXML(s);
		return scenarioList.contains(s.getName()) ? false: true;
	}

	/**
	 * Parses the data check scenarios xml.
	 *
	 * @return the list
	 */
	public static List<String> parseDataCheckScenariosXML(Scenario s) {
		
		try{
			 File inputFile = new File("./src/test/resources/DataCheckScenarios.xml");
		     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		     Document doc = dBuilder.parse(inputFile);
		     doc.getDocumentElement().normalize();
		     if (doc.hasChildNodes()) {
		 		setDataCheckScenarioList(doc.getChildNodes());
		 	}
		     
		}
		catch (Throwable e) {
	    	  System.out.println(AdoddleCommonStringPool.SPACE_STRING);
	      }
		
		/* Stub code to handle Null Pointer Exception */
		if(scenarioList.size() > 0)
			return scenarioList;
		else
			scenarioList.add(JavaUtils.getRandomNumber(10).toString());
		
		return scenarioList;
	}
	
	
	/**
	 * Sets the data check scenario list.
	 *
	 * @param nodeList the new data check scenario list
	 */
	public static void setDataCheckScenarioList(NodeList nodeList) {

	    for (int count = 0; count < nodeList.getLength(); count++) {
	    	Node tempNode = nodeList.item(count);
	    	if (tempNode.getNodeType() == Node.ELEMENT_NODE)  {
	    		if (tempNode.hasAttributes()) {
					NamedNodeMap nodeMap = tempNode.getAttributes();
					for (int index = 0; index < nodeMap.getLength(); index++) {
						Node node = nodeMap.item(index);
						if(node.getNodeName().equalsIgnoreCase("title") && tempNode.getParentNode().getAttributes().item(0).getNodeValue().equalsIgnoreCase(ResourceUtils.getExecutionEnvironment()))
							scenarioList.add(node.getNodeValue());
					}
				}
	    		
				if (tempNode.hasChildNodes()) 
					setDataCheckScenarioList(tempNode.getChildNodes());
			}
	    }
	 }
	
	@SuppressWarnings("static-access")
	public void setDataCollectionMap(Map<String, String> collectionDataMap) {
		this.collectionDataMap = collectionDataMap; 

	}
	
	@SuppressWarnings("static-access")
	public Map<String, String> getDataCollectionMap() {
		return this.collectionDataMap; 
	}
}
