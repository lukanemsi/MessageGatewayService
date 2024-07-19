package ge.nemsi.MessageGateway.gateway;

import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * {@inheritDoc}
 * specific gateway implementation for Netflix standards
 */
public class NetflixGateway implements GatewayI {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    {
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("sender", "message-gateway");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int send(String url, SmsRequest smsRequest, String message) {
        HttpEntity<SmsRequest> requestEntity = new HttpEntity<>(smsRequest, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response.getStatusCode().value();
    }
}