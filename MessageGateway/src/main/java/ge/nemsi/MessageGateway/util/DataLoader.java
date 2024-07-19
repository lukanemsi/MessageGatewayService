package ge.nemsi.MessageGateway.util;

import ge.nemsi.MessageGateway.entity.Client;
import ge.nemsi.MessageGateway.entity.ClientIP;
import ge.nemsi.MessageGateway.entity.Message;
import ge.nemsi.MessageGateway.entity.MessagePK;
import ge.nemsi.MessageGateway.repository.ClientIPRepository;
import ge.nemsi.MessageGateway.repository.ClientRepository;
import ge.nemsi.MessageGateway.repository.MessageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Luka Nemsitsveridze
 * @hidden
 * @role this class is created only the code user. it generates some testing values in database when project is run
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class DataLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final ClientIPRepository clientIPRepository;
    private final MessageRepository messageRepository;


    @Transactional
    @Override
    public void run(String... args) {
        log.info("data inserting started");
        if (clientRepository.findById("Google").isEmpty()) {
            log.info("google data inserting started");
            Client client = new Client();
            client.setClientName("Google");
            client.setUrl("https://httpbin.org/status/200");

            clientRepository.save(client);

            ClientIP googleIp1 = new ClientIP();
            googleIp1.setClient(client);
            googleIp1.setIp("127.0.0.1");

            ClientIP googleIp2 = new ClientIP();
            googleIp2.setClient(client);
            googleIp2.setIp("0:0:0:0:0:0:0:1");

            clientIPRepository.save(googleIp1);
            clientIPRepository.save(googleIp2);

            Message message1 = new Message(new MessagePK("Google", 11, 1), "Sending Google SMS Gateway random Code: XXXX");
            messageRepository.save(message1);
            Message message2 = new Message(new MessagePK("Google", 12, 2), "Google სმს, გაგზავნით კოდი: XXXX");
            messageRepository.save(message2);

            log.info("google data inserting finished successfully");
        }
        if (clientRepository.findById("Netflix").isEmpty()) {
            log.info("Netflix data inserting started");

            Client netflixClient = new Client();
            netflixClient.setClientName("Netflix");
            netflixClient.setUrl("https://httpbin.org/status/200");
            clientRepository.save(netflixClient);

            ClientIP netflixIp1 = new ClientIP();
            netflixIp1.setClient(netflixClient);
            netflixIp1.setIp("10.0.0.1");
            clientIPRepository.save(netflixIp1);

            ClientIP netflixIp2 = new ClientIP();
            netflixIp2.setClient(netflixClient);
            netflixIp2.setIp("10.0.0.2");
            clientIPRepository.save(netflixIp2);

            Message netflixMessage1 = new Message(new MessagePK("Netflix", 122, 1), "Sending Netflix SMS Gateway random Code: XXXX");
            messageRepository.save(netflixMessage1);

            Message netflixMessage2 = new Message(new MessagePK("Netflix", 23, 2), "Netflix კოდი: XXXX");
            messageRepository.save(netflixMessage2);

            log.info("Netflix data inserting finished successfully");
        }

        if (clientRepository.findById("Facebook").isEmpty()) {
            log.info("Facebook data inserting started");

            Client facebookClient = new Client();
            facebookClient.setClientName("Facebook");
            facebookClient.setUrl("https://httpbin.org/status/200");
            clientRepository.save(facebookClient);

            ClientIP facebookIp1 = new ClientIP();
            facebookIp1.setClient(facebookClient);
            facebookIp1.setIp("192.168.1.1");
            clientIPRepository.save(facebookIp1);

            ClientIP facebookIp2 = new ClientIP();
            facebookIp2.setClient(facebookClient);
            facebookIp2.setIp("192.168.1.2");
            clientIPRepository.save(facebookIp2);

            Message facebookMessage1 = new Message(new MessagePK("Facebook", 44, 1), "Sending Facebook SMS Gateway random Code: XXXX");
            messageRepository.save(facebookMessage1);

            Message facebookMessage2 = new Message(new MessagePK("Facebook", 47, 2), "Facebook სმს კოდი: XXXX");
            messageRepository.save(facebookMessage2);

            log.info("Facebook data inserting finished successfully");
        }

        log.info("data inserting finished successfully");
    }
}
