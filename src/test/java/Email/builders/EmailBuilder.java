package Email.builders;

import Email.Email;

import java.time.Instant;
import java.util.Collection;

public class EmailBuilder {

    private Instant creationDate;
    private String from;
    private Collection<String> to;
    private Collection<String> cc;
    private Collection<String> bcc;
    private String subject;
    private String message;

    public EmailBuilder setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;

        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;

        return this;
    }

    public EmailBuilder setFrom(String from) {
        this.from = from;

        return this;
    }

    public EmailBuilder setMessage(String message) {
        this.message = message;

        return this;
    }

    public EmailBuilder setTo(Collection<String> to) {
        this.to = to;

        return this;
    }

    public EmailBuilder setCc(Collection<String> cc) {
        this.cc = cc;

        return this;
    }

    public EmailBuilder setBcc(Collection<String> bcc) {
        this.bcc = bcc;

        return this;
    }

    public Email build() {

        return new Email(creationDate,from,to,cc,bcc,message,subject);
    }


}
