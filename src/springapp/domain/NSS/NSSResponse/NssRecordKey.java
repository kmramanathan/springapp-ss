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
 * <p>Java class for NssRecordKey complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NssRecordKey">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offenderID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="secureKey" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NssRecordKey", propOrder = {
    "offenderID",
    "state",
    "source",
    "secureKey"
})
public class NssRecordKey {

    @XmlElement(required = true)
    protected String offenderID;
    @XmlElement(required = true)
    protected String state;
    protected String source;
    protected String secureKey;

    /**
     * Gets the value of the offenderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOffenderID() {
        return offenderID;
    }

    /**
     * Sets the value of the offenderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffenderID(String value) {
        this.offenderID = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the source property.
     * 
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the secureKey property.
     * 
     */
    public String getSecureKey() {
        return secureKey;
    }

    /**
     * Sets the value of the secureKey property.
     * 
     */
    public void setSecureKey(String value) {
        this.secureKey = value;
    }

}
