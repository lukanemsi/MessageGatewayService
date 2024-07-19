package ge.nemsi.MessageGateway.gateway;

import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import ge.nemsi.MessageGateway.wrapper.SmsResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

/**
 * {@inheritDoc}
 */
@Log4j2
public class DefaultGateway implements GatewayI {

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
        SmsResult smsResult = new SmsResult();
        smsResult.setDecoded(smsRequest.isDecode());
        smsResult.setPhoneNumber(smsRequest.getPhoneNumber());
        smsResult.setMessage(message);
        log.info("sending message to default client");
        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("requestId", smsRequest.getRequestId())
                .queryParam("channel", smsRequest.getChannel())
                .build().encode().toUri();

        ResponseEntity<Object> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(smsResult, httpHeaders),
                Object.class);
        log.info("message sent to default client");
        return response.getStatusCode().value();
    }
}
