//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6-b31 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.11 at 01:16:40 PM IST 
//


package springapp.domain.NSS.NssDetailResponse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NssOffProductHolder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NssOffProductHolder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="USOneSearchKey" type="{}NssUSOneSearchKey" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NssOffProductHolder", propOrder = {
    "usOneSearchKey"
})
public class NssOffProductHolder {

    @XmlElement(name = "USOneSearchKey")
    protected List<NssUSOneSearchKey> usOneSearchKey;

    /**
     * Gets the value of the usOneSearchKey property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usOneSearchKey property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUSOneSearchKey().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NssUSOneSearchKey }
     * 
     * 
     */
    public List<NssUSOneSearchKey> getUSOneSearchKey() {
        if (usOneSearchKey == null) {
            usOneSearchKey = new ArrayList<NssUSOneSearchKey>();
        }
        return this.usOneSearchKey;
    }

}
