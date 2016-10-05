package springapp.manager;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.searchsystems.limestone.bean.BGCOffenderBean;
import net.searchsystems.limestone.bean.BGCRequestBean;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.commons.httpclient.CircularRedirectException;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import springapp.domain.NSS.NSSRequestBean;
import springapp.domain.NSS.NSSOffenderBean;

public class NSSRequestHelper {
	private static final Logger logger = Logger.getLogger("NssHelper");
	
	private static final String bgcUser = "searchsystems";
	private static final String bgcPassword = "!nt3grat!On1";
	private static final String bgcAccount = "10009807";	

	private String buildBGCProduct(String crimJurisdiction, boolean open) {
		String closeTag = "";
		if (!open) {
			closeTag = "/";
		}
		if (crimJurisdiction.equals("ALL")) {
			return "<" + closeTag + "USOneSearch>";
		} else {
			return "<" + closeTag + "SingleStateOneSearch>";
		}
	}
	
	private String buildBGCQueryStringState(String crimJurisdiction) {
		if (crimJurisdiction.equals("ALL")) {
			return "";
		} else {
			return "<state>" + crimJurisdiction + "</state>";
		}
	}
	
	private String buildBGCCustom(String crimJurisdiction) {
		//if (crimJurisdiction.equals("ALL")) {
		if (true) {
			String s =
			"<custom>" + 
				"<filters/>" +
				"<options>" +
					"<includeDetails>" + "YES" + "</includeDetails>" +
				"</options>" +
			"</custom>";
			return s;
		} else {
			return "";
		}
	}
	
	private String buildBGCQueryStringDOB(String crimDob) {
		String[] dobParts = crimDob.split("/");
		String dob = 
			"<DOB>" +
			"<month>" + dobParts[0] + "</month>" +
			"<day>"   + dobParts[1] + "</day>"   +
			"<year>"  + dobParts[2] + "</year>"  +
			"</DOB>";
		
		return dob;
	}	
	
	
	public String buildBGCQueryStringLogin() {
		String bgcUser = "searchsystems";
		String bgcPassword = "!nt3grat!On1";
		String bgcAccount = "10009807";
		
		String login = 
			"<login>" + 
			"<user>" + bgcUser + "</user>" +
			"<password>" + bgcPassword + "</password>" +
			"<account>" + bgcAccount + "</account>" +
			"</login>";
		
		return login;
	}

	public String buildBGCQueryStringUS(String crimFirstName, String crimLastName, String crimDob, String crimJurisdiction) {
		String query = 
			"<product>" +
			buildBGCProduct(crimJurisdiction, true) +
			"<order>" +			
			    "<lastName>" + crimLastName + "</lastName>" +
			    "<firstName>" + crimFirstName + "</firstName>" +			    
			    buildBGCQueryStringDOB(crimDob) +
			    buildBGCQueryStringState(crimJurisdiction) +			
			"</order>" +			
			buildBGCCustom(crimJurisdiction) +			
			buildBGCProduct(crimJurisdiction, false) +
			"</product>";
		
		return query;
	}
	
	
	/*
	 * xml/dom versions of above
	 */	
	protected OMElement buildLoginElement(OMFactory fac) {
		OMElement login = fac.createOMElement("login", null);
		
		OMElement user = fac.createOMElement("user", null);
		user.setText(bgcUser);
		login.addChild(user);
		
		OMElement password = fac.createOMElement("password", null);
		password.setText(bgcPassword);
		login.addChild(password);
		
		OMElement account = fac.createOMElement("account", null);
		account.setText(bgcAccount);
		login.addChild(account);
		
		return login;		
	}
	
	protected OMElement buildProductElement(OMFactory fac, NSSRequestBean bean) {
		OMElement eProduct = fac.createOMElement("product", null);
		
		OMElement eSearch;
			eSearch = fac.createOMElement("NationalSecurityOneSearch", null);
		
		eProduct.addChild(eSearch);		
		
		OMElement eOrder = buildOrderElement(fac, bean);
		//OMElement eCustom = buildCustomElement(fac, bean);

		eSearch.addChild(eOrder);		
		//eSearch.addChild(eCustom);
		
		return eProduct;		
	}
	
	protected OMElement buildProductElementUSOneSearchKey(OMFactory fac, NSSOffenderBean offenderBean) {
		OMElement eProduct = fac.createOMElement("product", null);

		OMElement eSearch = fac.createOMElement("USOneSearchKey", null);		
		eProduct.addChild(eSearch);		

		OMElement eOrder = fac.createOMElement("order", null);
		eSearch.addChild(eOrder);
		
		OMElement offenderID = fac.createOMElement("offenderID", null);
		OMElement state = fac.createOMElement("state", null);
		OMElement source = fac.createOMElement("source", null);
		OMElement secureKey = fac.createOMElement("secureKey", null);
		
		offenderID.setText(offenderBean.getRecordOffenderId());
		state.setText(offenderBean.getRecordState());
		source.setText(offenderBean.getRecordSource());
		secureKey.setText(offenderBean.getRecordSecureKey());
		
		eOrder.addChild(offenderID);
		eOrder.addChild(state);
		eOrder.addChild(source);
		eOrder.addChild(secureKey);
		
		eSearch.addChild(eOrder);	
		
		return eProduct;		
	}
	
	protected OMElement buildOrderElement(OMFactory fac, NSSRequestBean bean) {
		OMElement eOrder = fac.createOMElement("order", null);
		
		OMElement firstName = fac.createOMElement("firstName", null);
		//OMElement middleName = fac.createOMElement("middleName", null);
		OMElement lastName = fac.createOMElement("lastName", null);

		firstName.setText(bean.getFirstName());
		//middleName.setText(bean.getMiddleName());
		lastName.setText(bean.getLastName());

		eOrder.addChild(firstName);
		//eOrder.addChild(middleName);
		eOrder.addChild(lastName);
		/**
		 *  We don't need to add the dob for National Security Search, It will give no data, so make huge probs
		 *  that's why we avoid the DOB
		 */
		
		if(bean.getDobDay() != 0 && bean.getDobYear() != 0 && bean.getDobMonth() != 0)
		{
			OMElement eDOB = buildDOBElement(fac, bean);
			eOrder.addChild(eDOB);
		}
		
		
		
		return eOrder;
	}
	
	protected OMElement buildDOBElement(OMFactory fac, NSSRequestBean bean) {
		OMElement eDOB = fac.createOMElement("DOB", null);

		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("year",  bean.getDobYear());
		
			map.put("month", bean.getDobMonth());
			map.put("day",   bean.getDobDay());			
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			OMElement e = fac.createOMElement(entry.getKey(), null);
			Integer v = entry.getValue();
			if (v > 0) {
				e.setText(String.valueOf(entry.getValue()));
				eDOB.addChild(e);
			}
		}
		
		return eDOB;
	}
	
	protected OMElement buildFilterTypeElement(OMFactory fac, boolean exact) {
		OMElement eFilterType = fac.createOMElement("filterType", null);
		if (exact) {			
			eFilterType.setText("XM");
		} else {
			eFilterType.setText("FM");
		}
		return eFilterType;
	}
	
		/*
	 * build up the entire xml request from the methods above
	 */
	public String prepareXmlForBGCRequest(NSSRequestBean bean) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
        OMDocument doc = fac.createOMDocument();

        // switch namespace for BJL element
        OMElement eBGC = fac.createOMElement("BGC", null);
        doc.setOMDocumentElement(eBGC);
        
        OMElement eLogin = buildLoginElement(fac);        
        OMElement eProduct = buildProductElement(fac, bean);

        eBGC.addChild(eLogin);
        eBGC.addChild(eProduct);
        
        return doc.getOMDocumentElement().toString();
	}
	
	public String prepareXmlForOffenderSecureKey(NSSOffenderBean offenderBean) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
        OMDocument doc = fac.createOMDocument();

        // switch namespace for BJL element
        OMElement eBGC = fac.createOMElement("BGC", null);
        doc.setOMDocumentElement(eBGC);
        
        OMElement eLogin = buildLoginElement(fac);        
        OMElement eProduct = buildProductElementUSOneSearchKey(fac, offenderBean);

        eBGC.addChild(eLogin);
        eBGC.addChild(eProduct);
        
        return doc.getOMDocumentElement().toString();
	}
	
	/*

<?xml version="1.0" encoding="us-ascii" standalone="yes"?>
<BGC>
<login>
    <user>searchsystems</user>
    <password>!nt3grat!On1</password>        
    <account>10009807</account>
</login>
<product>
  <USOneSearch>
    <order>
      <lastName>Lowe</lastName>
      <firstName>Chris</firstName>
      <DOB><month>12</month><day>31</day><year>1976</year></DOB>
    </order>
  </USOneSearch>
</product>

</BGC>
	 */
	
	protected String buildXmlQueryByString(String crimFirstName, String crimLastName, String crimDob, String crimJurisdiction) {	
		String xmlReq = 
			"<?xml version='1.0' encoding='us-ascii' standalone='yes'?>" +
			"<BGC>" + 			
			this.buildBGCQueryStringLogin() +			
			this.buildBGCQueryStringUS(crimFirstName, crimLastName, crimDob, crimJurisdiction) +			
			"</BGC>";
		return xmlReq;
	}
	
	/*
	 * stage 2:

<?xml version="1.0" encoding="us-ascii" standalone="yes"?>
<BGC>
<login>
    <user>searchsystems</user>
    <password>!nt3grat!On1</password>        
    <account>10009807</account>
</login>
<product>
  <USOneSearchKey>
    <order>
      <offenderID/>
      <state/>
      <source/>
      <secureKey/>
    </order>
  </USOneSearch>
</product>
</BGC>

	 */
	
	/*
	protected String buildXmlQueryByStringStage1(String crimFirstName, String crimLastName, String crimDob, String crimJurisdiction) {	
		String xmlReq = 
			"<?xml version='1.0' encoding='us-ascii' standalone='yes'?>" +
			"<BGC>" + 			
			this.buildBGCQueryStringLogin() +			
			this.buildBGCQueryStringUS(crimFirstName, crimLastName, crimDob, crimJurisdiction) +			
			"</BGC>";
		return xmlReq;
	}
	
	protected String buildXmlQueryByStringStage2(String offenderID, String state, String source, String secureKey) {	
		String xmlReq = 
			"<?xml version='1.0' encoding='us-ascii' standalone='yes'?>" +
			"<BGC>" + 			
			this.buildBGCQueryStringLogin() +			
			this.buildBGCQueryStringUSOneSearchKey(offenderID, state, source, secureKey) +			
			"</BGC>";
		return xmlReq;
	}
	*/
	
	/**
	 * Send XML query to remote site using HTTP POST.
	 * 
	 */
	public Document doHttpPostQuery(String postURL, String xmlQuery) throws SearchException {
		Document xmlData = null;
		
		HttpClient client = new HttpClient();
		HttpClientParams params = client.getParams();
		//new DefaultMethodRetryHandler(1);

		// set client params
		params.setParameter(HttpClientParams.MAX_REDIRECTS, new Integer(45));
		params.setParameter(HttpClientParams.SO_TIMEOUT, new Integer(45 * 1000));
		//params.setParameter(HttpClientParams., new Integer(15));
		
		// initial connection timeout
		// separate from overall socket timeout above
		client.getHttpConnectionManager().getParams().setConnectionTimeout(60 * 1000);
		//client.getHttpConnectionManager().getParams().set
		
		PostMethod method = new PostMethod(postURL);
		NameValuePair xmlPost[] = {
			new NameValuePair("XML", xmlQuery)
		};
		method.setRequestBody(xmlPost);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
		
		// attempt the check
		try {
			logger.debug("Attempting to run National Security search");
			
			client.executeMethod(method);
			
			String sResponse = method.getResponseBodyAsString();
			String sr=sResponse.replaceAll("&#0", "");
			String str=sr.replaceAll("&#0", "&#0;");
			String s1=str.replaceAll("&#0;", "");
			String s2=s1.replaceAll("&#x0;", "");
			// XXX enable this to get the raw response from BGC
			logger.info("NSS response XML:"+s2);
		
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();		
			DocumentBuilder db = dbf.newDocumentBuilder();
			xmlData = db.parse(new InputSource(new StringReader(s2)));
			
			Element eDoc = xmlData.getDocumentElement();
			logger.info("orderId: " + eDoc.getAttribute("orderId"));			

			/*
			 * httpclient exceptions
			 */
		} catch (IllegalArgumentException e) {
			logger.error("HttpClient runtime exception", e);
			throw new SearchException(e);
		} catch (URIException e) {
			logger.error("Invalid URI", e);
			throw new SearchException(e);
		} catch (NullPointerException e) {
			logger.error("Null pointer (URI is null?)", e);
			throw new SearchException(e);
		} catch (CircularRedirectException e) {
			logger.error("Circular redirect", e);
			throw new SearchException(e);
		} catch (HttpException e) {
			logger.error("Fatal protocol violation", e);
			throw new SearchException(e);
		} catch (ConnectTimeoutException e) {
			logger.error("Timeout: connect", e);
			throw new SearchException(e);
		} catch (InterruptedIOException e) {
			logger.error("Timeout: interrupt", e);
			throw new SearchException(e);
		} catch (IOException e) {
			logger.error("Fatal transport error", e);
			throw new SearchException(e);
			/*
			 * xml parser exceptions
			 */
		} catch (ParserConfigurationException e) {
			String msg = "Couldn't load parser for XML response";
			logger.error(msg, e);
			throw new SearchException(e);
		} catch (SAXException e) {
			String msg = "Couldn't parse XML response";
			logger.error(msg, e);
			throw new SearchException(e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		
		return xmlData;
	}
}