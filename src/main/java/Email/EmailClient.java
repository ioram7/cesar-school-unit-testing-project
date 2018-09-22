package Email;

import java.time.Instant;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailClient implements EmailService {


    private Pattern regexPattern;
    private Matcher regMatcher;

    Collection<EmailAccount> accounts;
    EmailService emailService;
    String email;


    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;


    }

    public boolean isValidAddress(String emailAddress) {

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);

        if (regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isValidEmail(Email email) {


        boolean isValidTo = false;
        boolean isValidFrom = false;
        boolean isValidCc = false;
        boolean isValidBcc = false;
        boolean isValidCreateDate = false;

        for (String emailItem : email.getTo()) {

            if (isValidAddress(emailItem)) {

                isValidTo = true;
            }

        }

        for (String emailItem : email.getCc()) {

            if (isValidAddress(emailItem)) {

                isValidCc = true;
            }

        }

        for (String emailItem : email.getBcc()) {

            if (isValidAddress(emailItem)) {

                isValidBcc = true;
            }

        }

        if (isValidAddress(email.getFrom())) {

            isValidFrom = true;
        }

        if (email.getCreationDate() != null) {

            isValidCreateDate = true;
        }


        return isValidTo && isValidCc && isValidBcc && isValidFrom && isValidCreateDate;

    }


    public Collection<Email> emailList(EmailAccount account) {

        if (!(account.getPassword().length() > 6) || !account.verifyPasswordExpiration()) {
            return emailService.emailList(account);

        } else {
            throw new RuntimeException();
        }


    }

    public boolean sendEmail(Email email) {

        if (this.isValidEmail(email)) {
            return emailService.sendEmail(email);

        } else {
            throw new RuntimeException();
        }
    }

    public boolean createAccount(EmailAccount account) {

        boolean accountValue = false;

        if (isValidAddress(email) && account.getPassword().length() >= 6) {

            account.setLastPasswordUpdate(Instant.now());
            accounts.add(account);
            accountValue = true;


        }

        return accountValue;

    }

}
