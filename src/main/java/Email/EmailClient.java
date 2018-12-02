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


    public boolean isValidAddress(String emailAddress){

        boolean userIsValid = false;
        boolean domainIsValid = false;

        String[] listaEmail = emailAddress.split("@");

        String patternUser = "[a-zA-Z0-9\\u00C0-\\u00FF _.-]+";
        String patternDomain1 = "[a-zA-Z0-9\\u00C0-\\u00FF .]+";



        if (listaEmail[0].matches(patternUser)){

            userIsValid = true;

        }

        if (listaEmail.length >= 2){

            if (listaEmail[1].matches(patternDomain1))
            {
                domainIsValid = true;

            }

            if (listaEmail[1].startsWith(".") || listaEmail[1].endsWith(".") || listaEmail[1].contains(".."))
            {

                domainIsValid = false;
            }

        }

        return userIsValid && domainIsValid;

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

        boolean result = false;

        if (isValidEmail(email))
        {
            result = emailService.sendEmail(email);

        }else{

            throw new RuntimeException();

        }

        return result;

    }


    public boolean createAccount(EmailAccount account) {

        boolean accountValue = false;

        String email = account.getUser() + account.getDomain();

        if (isValidAddress(email) && account.getPassword().length() >= 6) {

            account.setLastPasswordUpdate(Instant.now());
            accountValue = true;


        }

        return accountValue;

    }

}
