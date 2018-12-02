package Email;

import java.time.Instant;
import java.time.temporal.ChronoUnit;



public class EmailAccount {

    public EmailAccount(String user, String domain, String password, Instant lastPasswordUpdate) {
        this.user = user;
        this.domain = domain;
        this.password = password;
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public String getUser() {
        return user;
    }

    private String user;

    public String getDomain() {
        return domain;
    }

    private String domain;

    public String getPassword() {
        return password;
    }

    private String password;

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setUser(String user) {

        this.user = user;
    }

    public void setDomain(String domain) {

        this.domain = domain;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    private Instant lastPasswordUpdate;

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public boolean validateUserRules(){

        return user.matches("[(.)|(\\-)|(_)0-9a-zA-Z]+");
    }

    public boolean validateDomainRules(){

        return domain.matches("\\b((?=[a-z0-9-]+\\.)[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,}\\b");
    }

    public boolean verifyPasswordExpiration() {


        Instant verify;
        Instant now = Instant.now();
        verify = now.minus(91, ChronoUnit.DAYS);

        if (verify.isAfter(lastPasswordUpdate)) {

            return true;

        } else {

            return false;
        }


    }

}