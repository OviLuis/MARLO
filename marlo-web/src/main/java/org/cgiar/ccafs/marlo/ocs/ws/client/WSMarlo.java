
package org.cgiar.ccafs.marlo.ocs.ws.client;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSMarlo", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSMarlo {


    /**
     * 
     * @param agreementId
     * @return
     *     returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloAgree>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMarloAgreements", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreements")
    @ResponseWrapper(localName = "getMarloAgreementsResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreementsResponse")
    public List<TWsMarloAgree> getMarloAgreements(
        @WebParam(name = "agreementId", targetNamespace = "")
        String agreementId);

    /**
     * 
     * @param agreementId
     * @return
     *     returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloAgreeCrp>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMarloAgreeCrp", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreeCrp")
    @ResponseWrapper(localName = "getMarloAgreeCrpResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreeCrpResponse")
    public List<TWsMarloAgreeCrp> getMarloAgreeCrp(
        @WebParam(name = "agreementId", targetNamespace = "")
        String agreementId);

    /**
     * 
     * @param agreementId
     * @return
     *     returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloAgreeCountry>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMarloAgreeCountry", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreeCountry")
    @ResponseWrapper(localName = "getMarloAgreeCountryResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloAgreeCountryResponse")
    public List<TWsMarloAgreeCountry> getMarloAgreeCountry(
        @WebParam(name = "agreementId", targetNamespace = "")
        String agreementId);

    /**
     * 
     * @param agreementId
     * @return
     *     returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloPla>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMarloPla", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloPla")
    @ResponseWrapper(localName = "getMarloPlaResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloPlaResponse")
    public List<TWsMarloPla> getMarloPla(
        @WebParam(name = "agreementId", targetNamespace = "")
        String agreementId);

    /**
     * 
     * @param plaId
     * @return
     *     returns java.util.List<org.cgiar.ciat.abw.control.logic.TWsMarloPlaCountry>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMarloPlaCountry", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloPlaCountry")
    @ResponseWrapper(localName = "getMarloPlaCountryResponse", targetNamespace = "http://logic.control.abw.ciat.cgiar.org/", className = "org.cgiar.ciat.abw.control.logic.GetMarloPlaCountryResponse")
    public List<TWsMarloPlaCountry> getMarloPlaCountry(
        @WebParam(name = "plaId", targetNamespace = "")
        String plaId);

}
