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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NssDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NssDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offenders" type="{}NssDetailOffenders" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="qtyFound" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qtyReturned" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NssDetail", propOrder = {
    "offenders"
})
public class NssDetail {

    protected List<NssDetailOffenders> offenders;
    @XmlAttribute(name = "sourceVersion")
    protected String sourceVersion;

    /**
     * Gets the value of the offenders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offenders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOffenders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NssDetailOffenders }
     * 
     * 
     */
    public List<NssDetailOffenders> getOffenders() {
        if (offenders == null) {
            offenders = new ArrayList<NssDetailOffenders>();
        }
        return this.offenders;
    }
    
    
    /**
     * Gets the value of the sourceVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceVersion() {
        return sourceVersion;
    }

    /**
     * Sets the value of the sourceVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceVersion(String value) {
        this.sourceVersion = value;
    }


}
