package utils;

public class ValidationUtils {

    public static boolean isValidMail(String mail) {
        return mail.matches(".+@[a-zA-Z]+\\.\\w+");
    }
}
