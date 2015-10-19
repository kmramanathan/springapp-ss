package springapp.manager;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import springapp.domain.NSS.NSSResponse.*;
import springapp.domain.NSS.NssDetailResponse.NssDetailOffence;
import springapp.domain.NSS.NssDetailResponse.NssOffMapHolder;
import springapp.domain.NSS.NssDetailResponse.NssRecordDetailSupplement;
import springapp.domain.NSS.NssDetailResponse.ObjectFactory;

import springapp.domain.NSS.NSSOffenderBean;
import springapp.domain.NSS.NSSOffenderMapperHolder;

public class NssTestParser {
	
	public static void main(String args[]) throws JAXBException
	{
		File file=new File("D:/NationalSecurity/NSS_Smith.xml");
		JAXBContext  jc=JAXBContext.newInstance(ObjectFactory.class);
		
		Unmarshaller um=jc.createUnmarshaller();
		//NSSOffenderMapperHolder nob= (NSSOffenderMapperHolder) um.unmarshal(file);
	//List<NSSOffenderBean> listob=nob.getListOffender();
		//NSSMapHolder nmh= (NSSMapHolder) um.unmarshal(file);
		JAXBElement<NssOffMapHolder> unMarshallObject= (JAXBElement<NssOffMapHolder>)um.unmarshal(ClassLoader.getSystemResourceAsStream("springapp/manager/NSS_offender.xml"));
		//NSSMapHolder NMHObj= unMarshallObject.getValue();
		NssOffMapHolder nomh= unMarshallObject.getValue();
		
//	    List<NssMapOffender> NMO=NMHObj.getProduct().getNationalSecurityOneSearch().getResponse().getSummary().getOffenders().getOffender();
	    //List<NssMapOffender> nm=NMHObj.getProduct().getUsOneSearchKey().getResponse().getDetail().getOffenders().getOffender();
	   
	   /* for (Iterator iterator = nm.iterator(); iterator.hasNext();) {
			NssMapOffender nssMapOffender = (NssMapOffender) iterator.next();
			//System.out.println("personal fullname:"+nssMapOffender.getOffenses().getOffense().getRecordDetails().getRecordDetail().getSupplements().getSupplement());
			
			sup.addAll(nssMapOffender.getOffenses().getOffense().getRecordDetails().getRecordDetail().getSupplements().getSupplement());
			
		}
	*/
	System.out.println(nomh.getOrderId());
	System.out.println(nomh.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0).getOffender().get(0).getOffenses().get(0).getOffense());
	
	List<NssDetailOffence> nmo=nomh.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0).getOffender().get(0).getOffenses().get(0).getOffense();
	List<NssRecordDetailSupplement> nrds=nomh.getProduct().get(0).getUSOneSearchKey().get(0).getResponse().get(0).getDetail().get(0).getOffenders().get(0).getOffender().get(0).getOffenses().get(0).getOffense().get(0).getRecordDetails().get(0).getRecordDetail().get(0).getSupplements().get(0).getSupplement();
	for (NssDetailOffence nssDetailOffence : nmo) {
		
		System.out.println(nssDetailOffence.getDescription());
	}
	    
	 for (Iterator iterator = nrds.iterator(); iterator.hasNext();) {
		NssRecordDetailSupplement nssRecordDetailSupplement = (NssRecordDetailSupplement) iterator
				.next();
		System.out.println(nssRecordDetailSupplement.getDisplayTitle());
		
	}
	
	}

}
