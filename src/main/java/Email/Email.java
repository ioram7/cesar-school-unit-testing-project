package Email;

import java.time.Instant;
import java.util.Collection;

public class Email {

        private Instant creationDate;
        private String from;
        private Collection<String> to;
        private Collection<String> cc;
        private Collection<String> bcc;
        private String subject;
        private String message;

        public Email(Instant creationDate,String from,Collection<String> to,
                     Collection<String> cc, Collection<String> bcc, String subject,String message){

            this.creationDate=creationDate;
            this.from = from;
            this.to = to;
            this.cc = cc;
            this.bcc = bcc;
            this.subject = subject;
            this.message = message;
        }

        public Instant getCreationDate() {
            return creationDate;
        }

        public Collection<String> getTo() {
            return to;
        }

        public String getFrom() {
            return from;
        }

        public Collection<String> getCc() {
            return cc;
        }

        public Collection<String> getBcc() {
            return bcc;
        }

        public String getSubject() {
        return subject;
        }

        public String getMessage() { return message;
        }

}
