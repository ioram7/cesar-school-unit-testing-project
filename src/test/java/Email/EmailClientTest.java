package Email;

import Email.builders.EmailBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


class EmailClientTest {

    private Email email;
    private EmailClient emailClient;
    private EmailService service;
    private ArrayList<String> toList;
    private ArrayList<String> ccList;
    private ArrayList<String> bccList;
    private Collection<Email> emails;

    public void setService(EmailService service){
        this.service = service;
    }


    @BeforeEach
    void setUp() {

        service = new EmailClient();
        toList = new ArrayList<>(Arrays.asList("email1@email.com", "email2@test.com"));
        ccList = new ArrayList<>(Arrays.asList("email3@test.com", "email4@test.com"));
        bccList = new ArrayList<>(Arrays.asList("email5@test.com", "email6@test.com"));
        emails = new ArrayList<>();


        emailClient = new EmailClient();

    }


    @Test
    void isValidAddress_true() {

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom("test@test.com")
                .setTo(toList)
                .setCc(ccList)
                .setBcc(bccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        Assertions.assertEquals(true, emailClient.isValidAddress(email.getFrom()));

    }

    @Test
    void isValidAddress_false() {

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(" tes!!@&test.com")
                .setTo(toList)
                .setCc(ccList)
                .setBcc(bccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        Assertions.assertEquals(true, emailClient.isValidAddress(email.getFrom()));

    }

    @Test
    void isValidEmail_testingCreationDate_true() {



    }


    @Test
    void emailList() {
    }



    @Test
    void sendEmail() {
    }

    @Test
    void createAccount() {
    }
}