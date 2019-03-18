package ru.fias;

import javax.annotation.Generated;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "House")
//@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class House {

    @XmlAttribute(name = "POSTALCODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String postalcode;
//    @XmlAttribute(name = "REGIONCODE")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String regioncode;
//    @XmlAttribute(name = "IFNSFL")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String ifnsfl;
//    @XmlAttribute(name = "TERRIFNSFL")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String terrifnsfl;
//    @XmlAttribute(name = "IFNSUL")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String ifnsul;
//    @XmlAttribute(name = "TERRIFNSUL")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String terrifnsul;
//    @XmlAttribute(name = "OKATO")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String okato;
//    @XmlAttribute(name = "OKTMO")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String oktmo;
//    @XmlAttribute(name = "UPDATEDATE", required = true)
//    @XmlSchemaType(name = "date")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected XMLGregorianCalendar updatedate;
    @XmlAttribute(name = "HOUSENUM")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String housenum;
//    @XmlAttribute(name = "ESTSTATUS", required = true)
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected BigInteger eststatus;
    @XmlAttribute(name = "BUILDNUM")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String buildnum;
//    @XmlAttribute(name = "STRUCNUM")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String strucnum;
    @XmlAttribute(name = "STRSTATUS")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger strstatus;
    @XmlAttribute(name = "HOUSEID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String houseid;
    @XmlAttribute(name = "HOUSEGUID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String houseguid;
    @XmlAttribute(name = "AOGUID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String aoguid;
//    @XmlAttribute(name = "STARTDATE", required = true)
//    @XmlSchemaType(name = "date")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected XMLGregorianCalendar startdate;
//    @XmlAttribute(name = "ENDDATE", required = true)
//    @XmlSchemaType(name = "date")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected XMLGregorianCalendar enddate;
//    @XmlAttribute(name = "STATSTATUS", required = true)
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected BigInteger statstatus;
//    @XmlAttribute(name = "NORMDOC")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String normdoc;
//    @XmlAttribute(name = "COUNTER", required = true)
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected BigInteger counter;
//    @XmlAttribute(name = "CADNUM")
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected String cadnum;
//    @XmlAttribute(name = "DIVTYPE", required = true)
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    protected int divtype;

    /**
     * Gets the value of the postalcode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPOSTALCODE() {
        return postalcode;
    }

    /**
     * Sets the value of the postalcode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPOSTALCODE(String value) {
        this.postalcode = value;
    }
//
//    /**
//     * Gets the value of the regioncode property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getREGIONCODE() {
//        return regioncode;
//    }
//
//    /**
//     * Sets the value of the regioncode property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setREGIONCODE(String value) {
//        this.regioncode = value;
//    }
//
//    /**
//     * Gets the value of the ifnsfl property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getIFNSFL() {
//        return ifnsfl;
//    }
//
//    /**
//     * Sets the value of the ifnsfl property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setIFNSFL(String value) {
//        this.ifnsfl = value;
//    }
//
//    /**
//     * Gets the value of the terrifnsfl property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getTERRIFNSFL() {
//        return terrifnsfl;
//    }
//
//    /**
//     * Sets the value of the terrifnsfl property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setTERRIFNSFL(String value) {
//        this.terrifnsfl = value;
//    }
//
//    /**
//     * Gets the value of the ifnsul property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getIFNSUL() {
//        return ifnsul;
//    }
//
//    /**
//     * Sets the value of the ifnsul property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setIFNSUL(String value) {
//        this.ifnsul = value;
//    }
//
//    /**
//     * Gets the value of the terrifnsul property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getTERRIFNSUL() {
//        return terrifnsul;
//    }
//
//    /**
//     * Sets the value of the terrifnsul property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setTERRIFNSUL(String value) {
//        this.terrifnsul = value;
//    }

//    /**
//     * Gets the value of the okato property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getOKATO() {
//        return okato;
//    }
//
//    /**
//     * Sets the value of the okato property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setOKATO(String value) {
//        this.okato = value;
//    }
//
//    /**
//     * Gets the value of the oktmo property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getOKTMO() {
//        return oktmo;
//    }
//
//    /**
//     * Sets the value of the oktmo property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setOKTMO(String value) {
//        this.oktmo = value;
//    }
//
//    /**
//     * Gets the value of the updatedate property.
//     *
//     * @return
//     *     possible object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public XMLGregorianCalendar getUPDATEDATE() {
//        return updatedate;
//    }
//
//    /**
//     * Sets the value of the updatedate property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setUPDATEDATE(XMLGregorianCalendar value) {
//        this.updatedate = value;
//    }

    /**
     * Gets the value of the housenum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSENUM() {
        return housenum;
    }

    /**
     * Sets the value of the housenum property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSENUM(String value) {
        this.housenum = value;
    }

    /**
     * Gets the value of the eststatus property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public BigInteger getESTSTATUS() {
//        return eststatus;
//    }
//
//    /**
//     * Sets the value of the eststatus property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link BigInteger }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setESTSTATUS(BigInteger value) {
//        this.eststatus = value;
//    }

    /**
     * Gets the value of the buildnum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getBUILDNUM() {
        return buildnum;
    }

    /**
     * Sets the value of the buildnum property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setBUILDNUM(String value) {
        this.buildnum = value;
    }

    /**
     * Gets the value of the strucnum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getSTRUCNUM() {
//        return strucnum;
//    }
//
//    /**
//     * Sets the value of the strucnum property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setSTRUCNUM(String value) {
//        this.strucnum = value;
//    }

    /**
     * Gets the value of the strstatus property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getSTRSTATUS() {
        return strstatus;
    }

    /**
     * Sets the value of the strstatus property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSTRSTATUS(BigInteger value) {
        this.strstatus = value;
    }

    /**
     * Gets the value of the houseid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSEID() {
        return houseid;
    }

    /**
     * Sets the value of the houseid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSEID(String value) {
        this.houseid = value;
    }

    /**
     * Gets the value of the houseguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSEGUID() {
        return houseguid;
    }

    /**
     * Sets the value of the houseguid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSEGUID(String value) {
        this.houseguid = value;
    }

    /**
     * Gets the value of the aoguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getAOGUID() {
        return aoguid;
    }

    /**
     * Sets the value of the aoguid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAOGUID(String value) {
        this.aoguid = value;
    }

//    /**
//     * Gets the value of the startdate property.
//     *
//     * @return
//     *     possible object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public XMLGregorianCalendar getSTARTDATE() {
//        return startdate;
//    }
//
//    /**
//     * Sets the value of the startdate property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setSTARTDATE(XMLGregorianCalendar value) {
//        this.startdate = value;
//    }

//    /**
//     * Gets the value of the enddate property.
//     *
//     * @return
//     *     possible object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public XMLGregorianCalendar getENDDATE() {
//        return enddate;
//    }
//
//    /**
//     * Sets the value of the enddate property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link XMLGregorianCalendar }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setENDDATE(XMLGregorianCalendar value) {
//        this.enddate = value;
//    }
//
//    /**
//     * Gets the value of the statstatus property.
//     *
//     * @return
//     *     possible object is
//     *     {@link BigInteger }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public BigInteger getSTATSTATUS() {
//        return statstatus;
//    }
//
//    /**
//     * Sets the value of the statstatus property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link BigInteger }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setSTATSTATUS(BigInteger value) {
//        this.statstatus = value;
//    }
//
//    /**
//     * Gets the value of the normdoc property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getNORMDOC() {
//        return normdoc;
//    }
//
//    /**
//     * Sets the value of the normdoc property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setNORMDOC(String value) {
//        this.normdoc = value;
//    }
//
//    /**
//     * Gets the value of the counter property.
//     *
//     * @return
//     *     possible object is
//     *     {@link BigInteger }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public BigInteger getCOUNTER() {
//        return counter;
//    }
//
//    /**
//     * Sets the value of the counter property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link BigInteger }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setCOUNTER(BigInteger value) {
//        this.counter = value;
//    }
//
//    /**
//     * Gets the value of the cadnum property.
//     *
//     * @return
//     *     possible object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public String getCADNUM() {
//        return cadnum;
//    }
//
//    /**
//     * Sets the value of the cadnum property.
//     *
//     * @param value
//     *     allowed object is
//     *     {@link String }
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setCADNUM(String value) {
//        this.cadnum = value;
//    }
//
//    /**
//     * Gets the value of the divtype property.
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public int getDIVTYPE() {
//        return divtype;
//    }
//
//    /**
//     * Sets the value of the divtype property.
//     *
//     */
//    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
//    public void setDIVTYPE(int value) {
//        this.divtype = value;
//    }

}
