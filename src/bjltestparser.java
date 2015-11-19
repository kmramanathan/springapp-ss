import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.jasper.runtime.ProtectedFunctionMapper;
import org.apache.torque.TorqueException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import springapp.domain.CorpXMLParser;
import springapp.domain.corporation.CorporationResults;
import springapp.domain.corporation.CorporationSearches;
import springapp.manager.SearchException;
import springapp.web.funnel.AbstractFunnelController;


public class bjltestparser extends AbstractFunnelController {

    
public static void main(String args[])
{
	
 
	Integer totalMatches = 0;
    Integer rowsReturned = 0;
    long searchId = 0;

    try{
    	JAXBContext jc= JAXBContext.newInstance(CorpXMLParser.class);
    	Unmarshaller u = jc.createUnmarshaller();
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	//Document doc = builder.parse(new InputSource(new StringReader(xmlResponse.replace("&nbsp;", "&#160;"))));
	 //local test mode state and international
	 //InputSource resxml = new InputSource(new FileInputStream(new File("C:/BJLResultnational.xml")));
    	InputSource resxml = new InputSource(new FileInputStream(new File("C:/corp.xml")));
    	Document doc=builder.parse(resxml);
    	CorpXMLParser obj=(CorpXMLParser)u.unmarshal(doc);
    	System.out.println("marshall:"+obj.getA_address1());
	 
    	    }
    
    catch (JAXBException e) {
		// TODO: handle exception
	}
    catch (Exception e) {
		// TODO: handle exception
	}
}
			public static  String readXMLNode(Node firstNode, String strTagName){
				String strQueryResult = "";
				Element firstElement = (Element)firstNode;
				NodeList queryResultList = firstElement.getElementsByTagName(strTagName);
				Element firstqueryResultElement = (Element)queryResultList.item(0);
				if(firstqueryResultElement!=null){
					NodeList textqueryResultList = firstqueryResultElement.getChildNodes();
					Node lNode = ((Node)textqueryResultList.item(0));			
					if(lNode!=null){
						strQueryResult = lNode.getNodeValue().trim();
					}
				}
				return (strQueryResult);
			}
}



		
