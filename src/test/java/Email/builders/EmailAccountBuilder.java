package Email.builders;

import Email.EmailAccount;

import java.time.Instant;

public class EmailAccountBuilder {

    protected String user;
    protected String domain;
    protected String password;
    protected Instant lastPasswordUpdate;

    public EmailAccountBuilder setUser(String user) {
        this.user = user;

        return this;
    }

    public EmailAccountBuilder setDomain(String domain) {
        this.domain = domain;

        return this;
    }

    public EmailAccountBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public EmailAccountBuilder setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;

        return this;
    }

    public EmailAccount build() {

        EmailAccount emailAccount = new EmailAccount(null, null, null, null);
        emailAccount.setUser(this.user);
        emailAccount.setDomain(this.domain);
        emailAccount.setPassword(this.password);
        emailAccount.setLastPasswordUpdate(this.lastPasswordUpdate);

        return emailAccount;
    }



}
