package springapp.domain.NSS;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement(name="BGC")
@XmlAccessorType(XmlAccessType.FIELD)
public class NSSOffenderMapperHolder {
	
	@XmlElement(name="offender")
	private ArrayList<NSSOffenderBean> listOffender;

	public ArrayList<NSSOffenderBean> getListOffender() {
		return listOffender;
	}

	public void setListOffender(ArrayList<NSSOffenderBean> listOffender) {
		this.listOffender = listOffender;
	}
	
	
	

}
