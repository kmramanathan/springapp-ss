//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6-b31 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.30 at 11:51:40 PM IST 
//


package springapp.domain.NSS.NSSResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NssOffenderIdentity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NssOffenderIdentity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="personal" type="{}NssOffenderPersonal"/>
 *         &lt;element name="address" type="{}NssOffenderAddress"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NssOffenderIdentity", propOrder = {
    "personal",
    "address"
})
public class NssOffenderIdentity {

    @XmlElement(required = true)
    protected NssOffenderPersonal personal;
    @XmlElement(required = true)
    protected NssOffenderAddress address;

    /**
     * Gets the value of the personal property.
     * 
     * @return
     *     possible object is
     *     {@link NssOffenderPersonal }
     *     
     */
    public NssOffenderPersonal getPersonal() {
        return personal;
    }

    /**
     * Sets the value of the personal property.
     * 
     * @param value
     *     allowed object is
     *     {@link NssOffenderPersonal }
     *     
     */
    public void setPersonal(NssOffenderPersonal value) {
        this.personal = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link NssOffenderAddress }
     *     
     */
    public NssOffenderAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link NssOffenderAddress }
     *     
     */
    public void setAddress(NssOffenderAddress value) {
        this.address = value;
    }

}
