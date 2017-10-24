package springapp.manager;

import java.util.ArrayList;
import java.util.HashMap;

import net.searchsystems.limestone.bean.BGCOffenseBean;
import net.searchsystems.limestone.bean.BGCOffenseSupplementBean;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BGCResponseHelper {
	private static final Logger logger = Logger.getLogger("BGCHelper");
	
	public ArrayList<BGCOffenderMapHolder> parseResponse(Element eOffenders) {
		return parseOffenders(eOffenders);
	}
	
	public ArrayList<BGCOffenderMapHolder> parseResponseForDDN(Element eOffenders) {
		return parseOffendersForDDN(eOffenders);
	}
	
	public BGCOffenderMapHolder parseResponseSingle(Document xmlData) {
//		//return parseOffenders(eOffender);
		// process the results	
		Element eRoot = (Element) xmlData.getDocumentElement();
		
		Element eProduct = (Element) eRoot.getElementsByTagName("product").item(0);
		Element eSearch = (Element) eProduct.getElementsByTagName("USOneSearchKey").item(0);;
		Element eResponse = (Element) eSearch.getElementsByTagName("response").item(0);
		Element eDetail = (Element) eResponse.getElementsByTagName("detail").item(0);
		Element eOffenders = (Element) eDetail.getElementsByTagName("offenders").item(0);
		Element eOffender = (Element) eOffenders.getElementsByTagName("offender").item(0);
		
		Integer qtyFound = Integer.parseInt(eOffenders.getAttribute("qtyFound"));
		Integer qtyReturned = Integer.parseInt(eOffenders.getAttribute("qtyReturned"));
		
		logger.info("found: " + qtyFound + ": returned: " + qtyReturned);
		
		// this is where all the work is to be done!
		return parseOffender(eOffender);
	}
	
	/*
	 * generic function to copy nodes into a HashMap
	 */
	protected HashMap<String,String> parseElementNodes(Element e, String[] nodes) {
		HashMap<String,String> map = new HashMap<String,String>();		
		for (String s : nodes) {
			map.put(s, e.getElementsByTagName(s).item(0).getFirstChild().getNodeValue()); //getTextContent());	
		}
		return map;
	}
	
	/*
	 * dump a map to the log
	 */
	protected void dumpMap(String text, HashMap<String,String> map) {
		for (String s : map.keySet()) {
			logger.debug(text + ": " + s + ":" + map.get(s));
		}
	}
	
	// parse the list of offenders
	protected ArrayList<BGCOffenderMapHolder> parseOffenders(Element eOffenders) {
		ArrayList<BGCOffenderMapHolder> list = new ArrayList<BGCOffenderMapHolder>();
		NodeList nlOffender = eOffenders.getElementsByTagName("offender");
		for (int i = 0; i < nlOffender.getLength(); i++) {
			Element eOffender = (Element) nlOffender.item(i);
			BGCOffenderMapHolder mh = parseOffender(eOffender);
			list.add(mh);
		}
		return list;
	}
	
	protected ArrayList<BGCOffenderMapHolder> parseOffendersForDDN(Element eOffenders) {
		ArrayList<BGCOffenderMapHolder> list = new ArrayList<BGCOffenderMapHolder>();
		NodeList nlOffender = eOffenders.getElementsByTagName("Record");
		for (int i = 0; i < nlOffender.getLength(); i++) {
			Element eOffender = (Element) nlOffender.item(i);
			BGCOffenderMapHolder mh = parseOffenderForDDN(eOffender);
			list.add(mh);
		}
		return list;
	}
	
	// parse a single offender
	// offender has: identity, aliasNames, aliasDOBs, record
	// identity has: personal, address
	protected BGCOffenderMapHolder parseOffender(Element e) {
		BGCOffenderMapHolder mh = new BGCOffenderMapHolder();

		Element eIdentity = (Element) e.getElementsByTagName("identity").item(0);
		
		// parse personal data
		Element ePersonal = (Element) eIdentity.getElementsByTagName("personal").item(0);				
		HashMap<String,String> mapPersonal = parsePersonal(ePersonal);
		dumpMap("personal", mapPersonal);
		mh.setMapPersonal(mapPersonal);

		// parse addresses
		Element eAddress = (Element) eIdentity.getElementsByTagName("address").item(0);
		String[] addressNodes = {
				"city", "street", "postalCode", "state", "county", "country",
			};
		HashMap<String,String> mapAddress = parseElementNodes(eAddress, addressNodes);
		dumpMap("address", mapAddress);
		mh.setMapAddress(mapAddress);
		
		// parse aliases
		// aliases has: name
		// name has: fields
		Element eAliasNames = (Element) e.getElementsByTagName("aliasNames").item(0);
		ArrayList<HashMap<String,String>> listAliases = parseAliases(eAliasNames);
		mh.setListAliases(listAliases);
		
		// parse record
		Element eRecord = (Element) e.getElementsByTagName("record").item(0);
		HashMap<String,String> mapRecord = parseRecord(eRecord);
		dumpMap("record", mapRecord);
		mh.setMapRecord(mapRecord);
		
		// parse offenses
		Element eOffenses = (Element) e.getElementsByTagName("offenses").item(0);
		if (eOffenses == null) {
			mh.setListOffenses(null);
		} else {
			ArrayList<BGCOffenseBean> listOffenses = parseOffenses(eOffenses);
			mh.setListOffenses(listOffenses);		
		}		
		
		return mh;
	}
	
	protected BGCOffenderMapHolder parseOffenderForDDN(Element e) {
		BGCOffenderMapHolder mh = new BGCOffenderMapHolder();

		Element eIdentity = (Element) e.getElementsByTagName("OnFileDetail").item(0);
		
		// parse personal data and address data
		String[] personalNodes = {
				"FirstName", "LastName", "MiddleName", "Address", "DOB", "Sex", "Race", "HairColor", "EyeColor", "PhotoURL", "Height", "Weight"
			};
		HashMap<String,String> mapPersonal = parseElementNodes(e, personalNodes);
		
		dumpMap("personal", mapPersonal);
		mh.setMapPersonal(mapPersonal);

		String[] addressNodes = {
				"Address"
			};
		HashMap<String,String> mapAddress = parseElementNodes(e, personalNodes);
		dumpMap("address", mapAddress);
		mh.setMapAddress(mapAddress);
		
		// parse aliases
		// aliases has: name
		// name has: fields
		/*Element eAliasNames = (Element) e.getElementsByTagName("aliasNames").item(0);
		ArrayList<HashMap<String,String>> listAliases = parseAliases(eAliasNames);
		mh.setListAliases(listAliases);*/
		
		// parse record
		/*Element eRecord = (Element) e.getElementsByTagName("Count").item(0);
		HashMap<String,String> mapRecord = parseRecord(eRecord);
		dumpMap("record", mapRecord);
		mh.setMapRecord(mapRecord);*/
		
		// parse offenses
		Element eOffenses = (Element) e.getElementsByTagName("Count").item(0);
		if (eOffenses == null) {
			mh.setListOffenses(null);
		} else {
			ArrayList<BGCOffenseBean> listOffenses = parseOffensesForDDN(e);
			mh.setListOffenses(listOffenses);		
		}		
		
		return mh;
	}
		
	protected HashMap<String,String> parsePersonal(Element e) {
		String[] personalNodes = {
			"fullName", "gender", "race", "weight", "DOB", "DOBType", "hairColor", "eyeColor", "imageURL"
		};
		HashMap<String,String> mapPersonal = parseElementNodes(e, personalNodes);
		
		// need to parse height separately
		Element eHeight = (Element) e.getElementsByTagName("height").item(0);
		String[] heightNodes = { "feet", "inches" };
		HashMap<String,String> mapHeight = parseElementNodes(eHeight, heightNodes);

		// merge
		mapPersonal.putAll(mapHeight);
		
		return mapPersonal;
	}
	
	/*
	protected HashMap<String,String> parseAddress(Element e) {
		String[] addressNodes = {
			"city", "street", "postalCode", "state", "county", "country",
		};
		return parseElementNodes(e, addressNodes);
	}
	
	protected HashMap<String,String> parseAlias(Element e) {		
		String[] aliasNodes = {
			"lastName", "firstName", "middleName", "suffix", 
		};
		return parseElementNodes(e, aliasNodes);
	}	
	*/
	
	protected ArrayList<HashMap<String,String>> parseAliases(Element eAliasNames) {
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String[] aliasNodes = {
				"lastName", "firstName", "middleName", "suffix", 
			};
		
		NodeList nlNames = eAliasNames.getElementsByTagName("name");
		for (int i = 0; i < nlNames.getLength(); i++) {
			Element eName = (Element) nlNames.item(i);			
			HashMap<String,String> map = parseElementNodes(eName, aliasNodes);
			dumpMap("alias", map);
			list.add(map);
		}
		
		//logger.info("parseAliases: found " + list.size());
		return list;
	}
	
	protected HashMap<String,String> parseRecord(Element eRecord) {			
		String[] nodesRecord = {
				"provider", "jurisdiction", "realNameMatch", "DOBMatch", "AKAMatch" 
		};
		HashMap<String,String> mapRecord = parseElementNodes(eRecord, nodesRecord);
		
		// sub element
		Element eKey = (Element) eRecord.getElementsByTagName("key").item(0);		
		String[] nodesKey = {
				"offenderID", "state", "source", "secureKey", 
		};
		HashMap<String,String> mapKey = parseElementNodes(eKey, nodesKey);
		
		// merge into 1 map
		mapRecord.putAll(mapKey);
		
		return mapRecord;
	}
	/*
	protected HashMap<String,String> parseOffense(Element e) {
		String[] nodes = {
				"description", "disposition", "degreeOfOffense", "sentence", "probation", "confinement",
				"arrestingAgency", "originatingAgency", "jurisdiction", "statute",
				"plea", "courtDecision", "courtCosts", "fine", "offenseDate", "arrestDate",
				"sentenceDate", "dispositionDate", "fileDate"
		};
		return parseElementNodes(e, nodes);
	}
	*/
	
	protected BGCOffenseBean parseOffense(Element e) {
		String[] nodes = {
				"description", "disposition", "degreeOfOffense", "sentence", "probation", "confinement",
				"arrestingAgency", "originatingAgency", "jurisdiction", "statute",
				"plea", "courtDecision", "courtCosts", "fine", "offenseDate", "arrestDate",
				"sentenceDate", "dispositionDate", "fileDate"
		};
		HashMap<String,String> map = parseElementNodes(e, nodes);
		BGCOffenseBean bean = new BGCOffenseBean();		
				
		bean.setFine(       map.get("fine"));
		bean.setPlea(       map.get("plea"));
		bean.setStatute(    map.get("statute"));
		bean.setSentence(   map.get("sentence"));
		bean.setFileDate(   map.get("fileDate"));		
		bean.setProbation(  map.get("probation"));
		bean.setArrestDate( map.get("arrestDate"));
		bean.setCourtCosts( map.get("courtCosts"));
		
		bean.setOffenseDate(   map.get("offenseDate"));
		bean.setConfinement(   map.get("confinement"));
		bean.setDescription(   map.get("description"));
		bean.setDisposition(   map.get("disposition"));
		bean.setSentenceDate(  map.get("sentenceDate"));
		bean.setJurisdiction(  map.get("jurisdiction"));
		bean.setCourtDecision( map.get("courtDecision"));
		
		bean.setDegreeOfOffense(   map.get("degreeOfOffense"));
		bean.setDispositionDate(   map.get("dispositionDate"));
		bean.setArrestingAgency(   map.get("arrestingAgency"));
		bean.setOriginatingAgency( map.get("originatingAgency"));
		
		// need to parse supp here		
		ArrayList<BGCOffenseSupplementBean> suppList = parseSupplements(e);
		bean.setBGCOffenseSupplementBeans(suppList);
		
		return bean;
	}
	
	
	protected BGCOffenseBean parseOffenseForDDN(Element e) {
		String[] nodes = {
				"Offense", "disposition", "degreeOfOffense", "sentence", "probation", "confinement",
				"arrestingAgency", "originatingAgency", "jurisdiction", "Statute",
				"plea", "courtDecision", "courtCosts", "fine", "OffenseDate", "arrestDate",
				"sentenceDate", "DispositionDate", "fileDate"
		};
		HashMap<String,String> map = parseElementNodes(e, nodes);
		BGCOffenseBean bean = new BGCOffenseBean();		
				
		bean.setFine(       map.get("fine"));
		bean.setPlea(       map.get("plea"));
		bean.setStatute(    map.get("statute"));
		bean.setSentence(   map.get("sentence"));
		bean.setFileDate(   map.get("fileDate"));		
		bean.setProbation(  map.get("probation"));
		bean.setArrestDate( map.get("arrestDate"));
		bean.setCourtCosts( map.get("courtCosts"));
		
		bean.setOffenseDate(   map.get("offenseDate"));
		bean.setConfinement(   map.get("confinement"));
		bean.setDescription(   map.get("description"));
		bean.setDisposition(   map.get("disposition"));
		bean.setSentenceDate(  map.get("sentenceDate"));
		bean.setJurisdiction(  map.get("jurisdiction"));
		bean.setCourtDecision( map.get("courtDecision"));
		
		bean.setDegreeOfOffense(   map.get("degreeOfOffense"));
		bean.setDispositionDate(   map.get("dispositionDate"));
		bean.setArrestingAgency(   map.get("arrestingAgency"));
		bean.setOriginatingAgency( map.get("originatingAgency"));
		
		// need to parse supp here		
		ArrayList<BGCOffenseSupplementBean> suppList = parseSupplements(e);
		bean.setBGCOffenseSupplementBeans(suppList);
		
		return bean;
	}
	
	
	// the xml is different for these	
	protected ArrayList<BGCOffenseSupplementBean> parseSupplements(Element e) {
		// XXX get right element first
		// e = offense
		// supp = offense/recordDetails/recordDetail/supplements (then each /supplement)
		Element eRecordDetails = (Element) e.getElementsByTagName("recordDetails").item(0);
		Element eRecordDetail = (Element) eRecordDetails.getElementsByTagName("recordDetail").item(0);
		Element eSupplements = (Element) eRecordDetail.getElementsByTagName("supplements").item(0);

		//HashMap<String,String> map = new HashMap<String,String>();
		ArrayList<BGCOffenseSupplementBean> list = new ArrayList<BGCOffenseSupplementBean>();
		NodeList nl = eSupplements.getElementsByTagName("supplement");
		for (int i = 0; i < nl.getLength(); i++) {
			Element eSupp = (Element) nl.item(i);
			Node eTitle = eSupp.getFirstChild();
			Node eValue = eSupp.getLastChild();
			if (eTitle.getNodeName().equals("displayTitle") && eValue.getNodeName().equals("displayValue")) {
				String sTitle = eTitle.getFirstChild().getNodeValue();//getTextContent();
				String sValue = eValue.getFirstChild().getNodeValue();//getTextContent();
				BGCOffenseSupplementBean bean = new BGCOffenseSupplementBean();
				bean.setDisplayTitle(sTitle);
				bean.setDisplayValue(sValue);
				//map.put(sTitle, sValue);
				list.add(bean);
			}
		}
		//return map;
		return list;
	}
	
	/*
	protected ArrayList<HashMap<String,String>> parseOffenses(Element eOffenses) {
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		NodeList nlOffenses = eOffenses.getElementsByTagName("offense");
		for (int i = 0; i < nlOffenses.getLength(); i++) {
			Element eOffense = (Element) nlOffenses.item(i);	
			HashMap<String,String> map = parseOffense(eOffense);
			dumpMap("offense", map);

			// parse supplements
			HashMap<String,String> mapSupp = parseSupplements(eOffense);
			dumpMap("supp", mapSupp);

			// merge
			map.putAll(mapSupp);			
			list.add(map);			
		}
		
		logger.info("parseOffenses: found " + list.size());
		return list;
	}
		
	protected ArrayList<HashMap<String,String>> parseOffenses(Element eOffenses, BGCOffenderMapHolder mh) {
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		NodeList nlOffenses = eOffenses.getElementsByTagName("offense");
		for (int i = 0; i < nlOffenses.getLength(); i++) {
			Element eOffense = (Element) nlOffenses.item(i);	
			HashMap<String,String> map = parseOffense(eOffense);
			dumpMap("offense", map);

			// parse supplements
			HashMap<String,String> mapSupp = parseSupplements(eOffense);
			mh.setMapSupplements(mapSupp);
			dumpMap("supp", mapSupp);

			// merge
			map.putAll(mapSupp);
			
			list.add(map);			
		}
		
		logger.info("parseOffenses: found " + list.size());
		return list;
	}
	*/
	/*
	protected ArrayList<HashMap<String,String>> parseOffenses(Element eOffenses) {
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		NodeList nlOffenses = eOffenses.getElementsByTagName("offense");
		for (int i = 0; i < nlOffenses.getLength(); i++) {
			Element eOffense = (Element) nlOffenses.item(i);	
			HashMap<String,String> map = parseOffense(eOffense);
			dumpMap("offense", map);

			// parse supplements
			HashMap<String,String> mapSupp = parseSupplements(eOffense);
			dumpMap("supp", mapSupp);

			// merge
			map.putAll(mapSupp);			
			list.add(map);			
		}
		
		logger.info("parseOffenses: found " + list.size());
		return list;
	}
	*/
	protected ArrayList<BGCOffenseBean> parseOffenses(Element eOffenses) {
		ArrayList<BGCOffenseBean> list = new ArrayList<BGCOffenseBean>();
		
		NodeList nlOffenses = eOffenses.getElementsByTagName("offense");
		for (int i = 0; i < nlOffenses.getLength(); i++) {
			Element eOffense = (Element) nlOffenses.item(i);	
			BGCOffenseBean bean = parseOffense(eOffense);
			list.add(bean);
		}
		
		logger.info("parseOffenses: found " + list.size());
		return list;
	}
	
	protected ArrayList<BGCOffenseBean> parseOffensesForDDN(Element eOffenses) {
		ArrayList<BGCOffenseBean> list = new ArrayList<BGCOffenseBean>();
		
		NodeList nlOffenses = eOffenses.getElementsByTagName("Count");
		for (int i = 0; i < nlOffenses.getLength(); i++) {
			Element eOffense = (Element) nlOffenses.item(i);	
			BGCOffenseBean bean = parseOffenseForDDN(eOffense);
			list.add(bean);
		}
		
		logger.info("parseOffenses: found " + list.size());
		return list;
	}
}