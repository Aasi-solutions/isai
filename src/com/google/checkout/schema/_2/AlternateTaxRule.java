//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.07.10 at 05:31:30 PM PDT 
//


package com.google.checkout.schema._2;


/**
 * Java content class for AlternateTaxRule complex type.
 * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/colinc/projects/apache-tomcat-5.5.12/apiv2.xsd line 322)
 * <p>
 * <pre>
 * &lt;complexType name="AlternateTaxRule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="tax-area">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="us-state-area" type="{http://checkout.google.com/schema/2}USStateArea"/>
 *                   &lt;element name="us-zip-area" type="{http://checkout.google.com/schema/2}USZipArea"/>
 *                   &lt;element name="us-country-area" type="{http://checkout.google.com/schema/2}USCountryArea"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 */
public interface AlternateTaxRule {


    /**
     * Gets the value of the taxArea property.
     * 
     * @return
     *     possible object is
     *     {@link com.google.checkout.schema._2.AlternateTaxRule.TaxAreaType}
     */
    com.google.checkout.schema._2.AlternateTaxRule.TaxAreaType getTaxArea();

    /**
     * Sets the value of the taxArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.google.checkout.schema._2.AlternateTaxRule.TaxAreaType}
     */
    void setTaxArea(com.google.checkout.schema._2.AlternateTaxRule.TaxAreaType value);

    /**
     * Gets the value of the rate property.
     * 
     */
    double getRate();

    /**
     * Sets the value of the rate property.
     * 
     */
    void setRate(double value);


    /**
     * Java content class for anonymous complex type.
     * <p>The following schema fragment specifies the expected content contained within this java content object. (defined at file:/home/colinc/projects/apache-tomcat-5.5.12/apiv2.xsd line 326)
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="us-state-area" type="{http://checkout.google.com/schema/2}USStateArea"/>
     *         &lt;element name="us-zip-area" type="{http://checkout.google.com/schema/2}USZipArea"/>
     *         &lt;element name="us-country-area" type="{http://checkout.google.com/schema/2}USCountryArea"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     */
    public interface TaxAreaType {


        /**
         * Gets the value of the usZipArea property.
         * 
         * @return
         *     possible object is
         *     {@link com.google.checkout.schema._2.USZipArea}
         */
        com.google.checkout.schema._2.USZipArea getUsZipArea();

        /**
         * Sets the value of the usZipArea property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.google.checkout.schema._2.USZipArea}
         */
        void setUsZipArea(com.google.checkout.schema._2.USZipArea value);

        /**
         * Gets the value of the usCountryArea property.
         * 
         * @return
         *     possible object is
         *     {@link com.google.checkout.schema._2.USCountryArea}
         */
        com.google.checkout.schema._2.USCountryArea getUsCountryArea();

        /**
         * Sets the value of the usCountryArea property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.google.checkout.schema._2.USCountryArea}
         */
        void setUsCountryArea(com.google.checkout.schema._2.USCountryArea value);

        /**
         * Gets the value of the usStateArea property.
         * 
         * @return
         *     possible object is
         *     {@link com.google.checkout.schema._2.USStateArea}
         */
        com.google.checkout.schema._2.USStateArea getUsStateArea();

        /**
         * Sets the value of the usStateArea property.
         * 
         * @param value
         *     allowed object is
         *     {@link com.google.checkout.schema._2.USStateArea}
         */
        void setUsStateArea(com.google.checkout.schema._2.USStateArea value);

    }

}
