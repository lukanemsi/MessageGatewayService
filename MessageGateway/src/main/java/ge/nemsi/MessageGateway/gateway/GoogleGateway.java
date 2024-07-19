package ge.nemsi.MessageGateway.gateway;

import ge.nemsi.MessageGateway.wrapper.SmsRequest;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.Collections;

/**
 * {@inheritDoc}
 * specific gateway implementation for Google standards
 */
@Log4j2
public class GoogleGateway implements GatewayI {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    {
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        httpHeaders.set("sender", "message-gateway");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int send(String url, SmsRequest smsRequest, String message) {
        String requestPayload = toXML(smsRequest);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestPayload, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getStatusCode().value();
    }

    private String toXML(SmsRequest smsRequest) {
        try {
            JAXBContext context = JAXBContext.newInstance(SmsRequest.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter writer = new StringWriter();
            marshaller.marshal(smsRequest, writer);
            return writer.toString();
        } catch (Exception e) {
            log.warn("error while marshaling message e: {}", e.getMessage());
        }
        return "";
    }
}
