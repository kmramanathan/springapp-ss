package springapp.manager;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParseDDNCriminalResult {

	private static String xml = "<OrderXML>" + 
			"   <Method>SEND ORDER</Method>" + 
			"   <Authentication>" + 
			"      <Username>tjkoster</Username>" + 
			"      <Password>tksearch2014</Password>" + 
			"   </Authentication>" + 
			"   <Status>SUCCESS</Status>" + 
			"   <ReferenceNumber>513970</ReferenceNumber>" + 
			"   <Order>" + 
			"      <Subject>" + 
			"         <FirstName>MARK</FirstName>" + 
			"         <MiddleName />" + 
			"         <LastName>MUSSELMAN</LastName>" + 
			"         <Suffix />" + 
			"         <DOB>10/17/1957</DOB>" + 
			"         <SSN>279-58-7797</SSN>" + 
			"      </Subject>" + 
			"      <ReportLink><![CDATA[/webservice/getreport.cfm?ReportID=513970&ReportKey=65EA70FA-F343-4E19-A035-22B6A5FDCD47]]></ReportLink>" + 
			"      <OrderDetail ServiceCode=\"Natcrim\" OrderId=\"20171014231914\" CRAorderId=\"523490\">" + 
			"         <Status>RECORD</Status>" + 
			"         <Result>" + 
			"            <OffenderCount>70</OffenderCount>" + 
			"            <Record>" + 
			"               <OnFileDetail>" + 
			"                  <FirstName>MARK</FirstName>" + 
			"                  <LastName>MUSSELMAN</LastName>" + 
			"                  <DOB>10/17/1957</DOB>" + 
			"                  <Sex>Male</Sex>" + 
			"                  <Race>White</Race>" + 
			"               </OnFileDetail>" + 
			"               <VerifiedBy>Exact Name And DOB Match</VerifiedBy>" + 
			"               <Count>" + 
			"                  <Offense>Not Specified</Offense>" + 
			"                  <Sentence>CORRECTIONAL RECEPTION CENTER</Sentence>" + 
			"                  <IncarcerationDate>06/08/2007</IncarcerationDate>" + 
			"                  <SourceofRecord>Ohio Department of Corrections</SourceofRecord>" + 
			"                  <OffenseData>" + 
			"                     <OffenseDetail title=\"Inmate Number\">55513900</OffenseDetail>" + 
			"                  </OffenseData>" + 
			"                  <IncarcerationData>" + 
			"                     <IncarcerationDetail title=\"Date Released\">07/24/2015</IncarcerationDetail>" + 
			"                     <IncarcerationDetail title=\"Facility\">CORRECTIONAL RECEPTION CENTER</IncarcerationDetail>" + 
			"                  </IncarcerationData>" + 
			"               </Count>" + 
			"            </Record>" +
			"		</Result>" +
			"		</OrderDetail>" +
			"   </Order>" +
			"</OrderXML>";
			
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlData = db.parse(new InputSource(new StringReader(xml)));
		
		// process the results	
		Element eRoot = (Element) xmlData.getDocumentElement();
		
		Element eProduct = (Element) eRoot.getElementsByTagName("Order").item(0);
		Element eSearch;
		eSearch = (Element) eProduct.getElementsByTagName("OrderDetail").item(0);
		
		Element eResponse = (Element) eSearch.getElementsByTagName("Result").item(0);

		//Element eSummary = (Element) eResponse.getElementsByTagName("summary").item(0);
		//Element eDetail = (Element) eResponse.getElementsByTagName("detail").item(0);
		/*Element eOffenders;
		if (eDetail == null) {
			if (eSummary == null) {
				String msg = "eSummary & eDetail are both null? : ";
				logger.error(msg + eRoot.toString());
				throw new SearchException("eSummary & eDetail are both null?");
			} else {
				eOffenders = (Element) eSummary.getElementsByTagName("offenders").item(0);
			}
		} else {
			eOffenders = (Element) eDetail.getElementsByTagName("offenders").item(0);
		}*/
		
		Integer qtyFound = Integer.parseInt(eResponse.getElementsByTagName("OffenderCount").item(0).getFirstChild().getNodeValue());
		Integer qtyReturned = qtyFound;
		
		System.out.print("found: " + qtyFound + ": returned: " + qtyReturned);

	}

}
