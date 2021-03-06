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
 * <p>Java class for NssOffendeRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NssOffendeRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provider" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="jurisdiction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="realNameMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DOBMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AKAMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="key" type="{}NssRecordKey"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NssOffendeRecord", propOrder = {
    "provider",
    "jurisdiction",
    "realNameMatch",
    "dobMatch",
    "akaMatch",
    "key"
})
public class NssOffendeRecord {

    @XmlElement(required = true)
    protected String provider;
    @XmlElement(required = true)
    protected String jurisdiction;
    @XmlElement(required = true)
    protected String realNameMatch;
    @XmlElement(name = "DOBMatch", required = true)
    protected String dobMatch;
    @XmlElement(name = "AKAMatch", required = true)
    protected String akaMatch;
    @XmlElement(required = true)
    protected NssRecordKey key;

    /**
     * Gets the value of the provider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvider(String value) {
        this.provider = value;
    }

    /**
     * Gets the value of the jurisdiction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJurisdiction() {
        return jurisdiction;
    }

    /**
     * Sets the value of the jurisdiction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJurisdiction(String value) {
        this.jurisdiction = value;
    }

    /**
     * Gets the value of the realNameMatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealNameMatch() {
        return realNameMatch;
    }

    /**
     * Sets the value of the realNameMatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealNameMatch(String value) {
        this.realNameMatch = value;
    }

    /**
     * Gets the value of the dobMatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOBMatch() {
        return dobMatch;
    }

    /**
     * Sets the value of the dobMatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOBMatch(String value) {
        this.dobMatch = value;
    }

    /**
     * Gets the value of the akaMatch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAKAMatch() {
        return akaMatch;
    }

    /**
     * Sets the value of the akaMatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAKAMatch(String value) {
        this.akaMatch = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link NssRecordKey }
     *     
     */
    public NssRecordKey getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link NssRecordKey }
     *     
     */
    public void setKey(NssRecordKey value) {
        this.key = value;
    }

}
