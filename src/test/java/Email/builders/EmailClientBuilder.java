package Email.builders;

import Email.EmailAccount;
import Email.EmailClient;
import Email.EmailService;

import java.util.Collection;

public class EmailClientBuilder {

    private Collection<EmailAccount> accounts;
    private EmailService emailService;

    public EmailClientBuilder() {

    }

    public EmailClientBuilder setAccounts(Collection<EmailAccount> accounts){
        this.accounts=accounts;
        return this;
    }

    public EmailClientBuilder setEmailService(EmailService emailService){
        this.emailService=emailService;
        return this;
    }

    public EmailClient build(){

        return new EmailClient();

}
}
