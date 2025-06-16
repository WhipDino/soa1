package com.faculdade.vicioapostas.controller;

import org.springframework.stereotype.Component;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@Component
@WebService(serviceName = "ApoioService")
public class ApoioSoapService {
    
    @WebMethod
    public String obterInformacoesApoio(@WebParam(name = "tipoApoio") String tipoApoio) {
        switch (tipoApoio.toUpperCase()) {
            case "EMERGENCIA":
                return "Em caso de emergência, ligue para 188 (CVV) ou procure ajuda médica imediatamente.";
            case "GRUPO":
                return "Grupos de apoio estão disponíveis às terças e quintas, das 19h às 21h.";
            case "CONSULTA":
                return "Consultas individuais podem ser agendadas através do sistema ou pelo telefone (11) 1234-5678.";
            default:
                return "Para mais informações sobre apoio ao vício em apostas, entre em contato conosco.";
        }
    }
    
    @WebMethod
    public String verificarDisponibilidade(@WebParam(name = "data") String data) {
        // Simulação simples de verificação de disponibilidade
        return "Horários disponíveis para " + data + ": 09:00, 14:00, 16:00";
    }
}

