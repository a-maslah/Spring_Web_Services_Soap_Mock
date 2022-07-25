package com.example.demospringws.ws;

import com.adria.soap.GetCompteRequest;
import com.adria.soap.GetCompteResponse;
import com.adria.soap.ListComptesRequest;
import com.adria.soap.ListComptesResponse;
import com.example.demospringws.entites.Compte;
import com.example.demospringws.entites.reponsitory.CompteRepository;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;
import java.util.List;

@Endpoint
public class BanqueSoapEndPoint {

    @Autowired
    private CompteRepository compteRepository;


    public static final String Name_SPACE="http://www.adria.com/soap";
    @ResponsePayload
    @PayloadRoot(localPart = "GetCompteRequest",namespace = Name_SPACE)
    public GetCompteResponse consulterCompte(@RequestPayload GetCompteRequest request) throws DatatypeConfigurationException {
        GetCompteResponse response = new GetCompteResponse();
        Compte compteMetier = compteRepository.findById(request.getCode()).get();

        com.adria.soap.Compte soapCompte = mapSoapCompteFrom(compteMetier);
        response.setCompte(soapCompte);

        return response;

    }

    @ResponsePayload
    @PayloadRoot(localPart = "ListComptesRequest",namespace = Name_SPACE)

    public ListComptesResponse listeComptes(@RequestPayload ListComptesRequest request) throws DatatypeConfigurationException {

        ListComptesResponse response = new ListComptesResponse();
        List<Compte> comptes = compteRepository.findAll();
        for (Compte c:comptes){
            response.getCompte().add(mapSoapCompteFrom(c));
        }
        return response;
    }

    private com.adria.soap.Compte mapSoapCompteFrom(Compte compteMetier) throws DatatypeConfigurationException {
        com.adria.soap.Compte compte = new com.adria.soap.Compte();
        compte.setCode(compteMetier.getCode());
        compte.setSolde(compteMetier.getSolde());
        compte.setState(compteMetier.getEtat());
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(compteMetier.getDateCreation());
        compte.setDateCreation(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
        return compte;
    }


}

