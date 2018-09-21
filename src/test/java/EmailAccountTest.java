import Email.EmailAccountBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class EmailAccountTest {


    private EmailAccountBuilder emailAccountBuilder;


    @BeforeEach
    void setUp(){
        emailAccountBuilder = new EmailAccountBuilder();
    }



    @Test
    void verifyUserRules_true() {

        emailAccountBuilder.setUser("test")
                .setDomain("test.com")
                .setPassword("123456");
        Assertions.assertEquals(true, emailAccountBuilder.build().validateUserRules());
    }

    @Test
    void verifyUserRules_false() {

        emailAccountBuilder.setUser("!!test")
                .setDomain("test.com")
                .setPassword("123456");
        Assertions.assertEquals(true, emailAccountBuilder.build().validateUserRules());
    }

    @Test
    void validateDomainRules_true() {

        emailAccountBuilder.setUser("test")
                .setDomain("test.com")
                .setPassword("123456");
        Assertions.assertEquals(true, emailAccountBuilder.build().validateDomainRules());
    }

    @Test
    void validateDomainRules_false() {

        emailAccountBuilder.setUser("test")
                .setDomain("..test.com")
                .setPassword("123456");
        Assertions.assertEquals(false, emailAccountBuilder.build().validateDomainRules());
    }

    @Test
    void verifyPasswordExpiration_true() {

        emailAccountBuilder.setUser("test").
                setDomain("test.com").
                setPassword("123456").
                setLastPasswordUpdate(Instant.now());
        Assertions.assertEquals(true, emailAccountBuilder.build().verifyPasswordExpiration());

    }

    @Test

    void verifyPasswordExpiration_false(){

        emailAccountBuilder.setUser("test").
                setDomain("test.com").
                setPassword("123456").
                setLastPasswordUpdate(Instant.now());

        Assertions.assertEquals(false,emailAccountBuilder.build().verifyPasswordExpiration());
    }


}
