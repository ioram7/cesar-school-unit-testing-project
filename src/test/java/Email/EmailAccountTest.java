package Email;

import Email.builders.EmailAccountBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class EmailAccountTest {


    private EmailAccountBuilder emailAccountBuilder;

    private static final String VALID_USER = "roberto";
    private static final String INVALID_USER = "! *roberto";
    private static final String VALID_PASSWORD = "123456";
    private static final String INVALID_PASSWORD = "54{}+!# dFDF...";
    private static final String VALID_DOMAIN = "test.com";
    private static final String INVALID_DOMAIN = "..test.com";
    private static final int DAYS_100 = -100;


    @BeforeEach
    void setUp(){
        emailAccountBuilder = new EmailAccountBuilder();
    }



    @Test
    void verifyUserRules_true() {

        emailAccountBuilder.setUser(VALID_USER)
                .setDomain(VALID_DOMAIN)
                .setPassword(VALID_PASSWORD);
        Assertions.assertEquals(true, emailAccountBuilder.build().validateUserRules());
    }

    @Test
    void verifyUserRules_false() {

        emailAccountBuilder.setUser(INVALID_USER)
                .setDomain(VALID_DOMAIN)
                .setPassword(VALID_PASSWORD);
        Assertions.assertEquals(false, emailAccountBuilder.build().validateUserRules());
    }

    @Test
    void validateDomainRules_true() {

        emailAccountBuilder.setUser(VALID_USER)
                .setDomain(VALID_DOMAIN)
                .setPassword(VALID_PASSWORD);
        Assertions.assertEquals(true, emailAccountBuilder.build().validateDomainRules());
    }

    @Test
    void validateDomainRules_false() {

        emailAccountBuilder.setUser(VALID_USER)
                .setDomain(INVALID_DOMAIN)
                .setPassword(VALID_PASSWORD);
        Assertions.assertEquals(false, emailAccountBuilder.build().validateDomainRules());
    }

    @Test
    void verifyPasswordExpiration_true() {

        emailAccountBuilder.setUser(VALID_USER).
                setDomain(VALID_DOMAIN).
                setPassword(VALID_PASSWORD).
                setLastPasswordUpdate(Instant.now().plus(DAYS_100, ChronoUnit.DAYS));
        Assertions.assertEquals(true, emailAccountBuilder.build().verifyPasswordExpiration());

    }

    @Test

    void verifyPasswordExpiration_false(){

        emailAccountBuilder.setUser(VALID_USER).
                setDomain(VALID_DOMAIN).
                setPassword(INVALID_PASSWORD).
                setLastPasswordUpdate(Instant.now());

        Assertions.assertEquals(false,emailAccountBuilder.build().verifyPasswordExpiration());
    }


}
