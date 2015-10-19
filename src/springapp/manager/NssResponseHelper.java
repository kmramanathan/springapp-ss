package springapp.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import springapp.domain.NSS.NSSResponse.NSSMapHolder;
import springapp.domain.NSS.NSSResponse.NssMapOffender;
import springapp.domain.NSS.NSSResponse.NssOffenders;
import springapp.domain.NSS.NSSResponse.ObjectFactory;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffence;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffenders;
import springapp.domain.NSS.NssDetailResponse.NssOffMapHolder;
import springapp.domain.NSS.NssDetailResponse.NssRecordDetailSupplement;
/**
 * 
 * @author Vivek
 *
 */
public class NssResponseHelper {
	
	private static final Logger logger=Logger.getLogger("NssParser");
	
	/**
	 * Map the Offender record with database
	 * @return
	 * @throws JAXBException 
	 */
	
	public List<NssMapOffender> parseOffenders(Document xmldata) throws JAXBException
	{
		
		JAXBContext jaxbContext= JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller um = jaxbContext.createUnmarshaller();
		
		JAXBElement<NSSMapHolder> unmarshallObj= (JAXBElement<NSSMapHolder>) um.unmarshal(xmldata);
		NSSMapHolder mapHolder= unmarshallObj.getValue();
		List<NssMapOffender> offenderList=mapHolder.getProduct().getNationalSecurityOneSearch().getResponse().getSummary().getOffenders().getOffender();
		return offenderList;
	}
	
	/* add offense record*/
	public List<NssDetailOffence> parseOffense(Document xmlData) throws JAXBException
	{
		JAXBContext jaxContext = JAXBContext.newInstance(springapp.domain.NSS.NssDetailResponse.ObjectFactory.class);
		Unmarshaller um = jaxContext.createUnmarshaller();
		JAXBElement<NssOffMapHolder> Obj=(JAXBElement<NssOffMapHolder>) um.unmarshal(xmlData);
		NssOffMapHolder mapHolder = Obj.getValue();
		List<NssDetailOffence> offenseList = mapHolder.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0).getOffender().get(0).getOffenses().get(0).getOffense();
		
		
		return offenseList; 
		
	}
	
	/**
	 * @throws JAXBException 
	 * 
	 */
	public List<NssRecordDetailSupplement> parseSupplemets(Document xmdata) throws JAXBException
	{
		JAXBContext jaxContext = JAXBContext.newInstance(springapp.domain.NSS.NssDetailResponse.ObjectFactory.class);
		Unmarshaller um = jaxContext.createUnmarshaller();
		JAXBElement<NssOffMapHolder> Obj=(JAXBElement<NssOffMapHolder>) um.unmarshal(xmdata);
		NssOffMapHolder mapHolder = Obj.getValue();
		
		List<NssRecordDetailSupplement> supplemetList= mapHolder.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0).getOffender().get(0).getOffenses().get(0).getOffense().get(0).getRecordDetails().get(0).getRecordDetail().get(0).getSupplements().get(0).getSupplement();
		return supplemetList;
		
	}
	
	public NssOffenders parseOffendersFound(Document xmldata) throws JAXBException
	{
		JAXBContext jaxbContext= JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller um = jaxbContext.createUnmarshaller();
		
		JAXBElement<NSSMapHolder> unmarshallObj= (JAXBElement<NSSMapHolder>) um.unmarshal(xmldata);
		NSSMapHolder mapHolder= unmarshallObj.getValue();
		NssOffenders QueryFound=mapHolder.getProduct().getNationalSecurityOneSearch().getResponse().getSummary().getOffenders();
		return QueryFound;
	}
	public NssDetailOffenders parseDetailOffendersFound(Document xmlData) throws JAXBException
	{
		JAXBContext jaxContext = JAXBContext.newInstance(springapp.domain.NSS.NssDetailResponse.ObjectFactory.class);
		Unmarshaller um = jaxContext.createUnmarshaller();
		JAXBElement<NssOffMapHolder> Obj=(JAXBElement<NssOffMapHolder>) um.unmarshal(xmlData);
		NssOffMapHolder mapHolder = Obj.getValue();
		NssDetailOffenders queryFound = mapHolder.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0);
		
		return queryFound;
		
	}
	
	
	

}
