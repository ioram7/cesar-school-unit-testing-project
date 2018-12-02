package Email;

import Email.builders.EmailAccountBuilder;
import Email.builders.EmailBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class EmailClientTest {


    private Email email;
    private EmailBuilder emailbuilder;
    private EmailClient emailClient;
    private EmailAccount emailAccount;
    private Collection<Email> emails;
    private ArrayList<String> validToList;
    private ArrayList<String> validCcList;
    private ArrayList<String> validBccList;
    private ArrayList<String> invalidToList;
    private ArrayList<String> invalidCcList;
    private ArrayList<String> invalidBccList;


    private static final String VALID_USER = "roberto";
    private static final String VALID_EMAIL = "test@test.com";
    private static final String INVALID_EMAIL = " tes!!@&test.com";
    private static final String VALID_PASSWORD = "1234567";
    private static final String INVALID_PASSWORD = "1";
    private static final String VALID_DOMAIN = "@test.com";
    private static final String INVALID_DOMAIN = "..test.com";


    @BeforeEach
    void setUp() {

        emailClient = new EmailClient();

        validToList = new ArrayList<>(Arrays.asList("test1@test.com", "test2@test.com"));
        validCcList = new ArrayList<>(Arrays.asList("test1@test.com", "test2@test.com"));
        validBccList = new ArrayList<>(Arrays.asList("test3@test.com", "test4@test.com"));
        invalidBccList = new ArrayList<>(Arrays.asList("test3@..test.com", "test4@test.com"));

        emails = new ArrayList<>();

    }


    @Test
    void isValidAddress_true() {

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(VALID_EMAIL)
                .setTo(validToList)
                .setCc(validCcList)
                .setBcc(validBccList)
                .setSubject("test")
                .setMessage("test")
                .build();


        assertEquals(true, emailClient.isValidAddress(email.getFrom()));

    }

    @Test
    void isValidAddress_false() {

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(INVALID_EMAIL)
                .setTo(validToList)
                .setCc(validCcList)
                .setBcc(validBccList)
                .setSubject("test")
                .setMessage("test")
                .build();


        assertEquals(false, emailClient.isValidAddress(email.getFrom()));

    }


    @Test
    void isValidEmail_validScenario() {

        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(VALID_EMAIL)
                .setTo(validToList)
                .setCc(validCcList)
                .setBcc(validBccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        assertEquals(true, emailClient.isValidEmail(email));

    }



    @Test
    void isValidEmail_invalidScenario_invalidTo() {

        invalidToList = new ArrayList<>(Arrays.asList("test1@..test.com", "test2@test.com"));
        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(VALID_EMAIL)
                .setTo(invalidToList)
                .setCc(validCcList)
                .setBcc(validBccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        assertEquals(false, emailClient.isValidEmail(email));

    }



    @Test
    void isValidEmail_invalidScenario_invalidCc() {

        invalidCcList = new ArrayList<>(Arrays.asList("test1@..test.com", "test2@test.com"));
        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(VALID_EMAIL)
                .setTo(validToList)
                .setCc(invalidCcList)
                .setBcc(validBccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        assertEquals(false, emailClient.isValidEmail(email));

    }


    @Test
    void isValidEmail_invalidScenario_invalidBcc() {

        invalidBccList = new ArrayList<>(Arrays.asList("test3@..test.com", "test4@test.com"));
        email = new EmailBuilder()
                .setCreationDate(Instant.now())
                .setFrom(VALID_EMAIL)
                .setTo(validToList)
                .setCc(validCcList)
                .setBcc(invalidBccList)
                .setSubject("test")
                .setMessage("test")
                .build();

        assertEquals(false, emailClient.isValidEmail(email));

    }

    @Test
    void createAccount_true() {

        emailAccount = new EmailAccountBuilder()
                .setUser(VALID_USER)
                .setDomain(VALID_DOMAIN)
                .setPassword(VALID_PASSWORD)
                .setLastPasswordUpdate(Instant.now())
                .build();

        assertTrue(emailClient.createAccount(emailAccount));
    }


    @Test
    void createAccount_False(){

        emailAccount = new EmailAccountBuilder()
                .setUser(VALID_USER)
                .setDomain(INVALID_DOMAIN)
                .setPassword(INVALID_PASSWORD)
                .build();

        assertFalse(emailClient.createAccount(emailAccount));
    }


}








