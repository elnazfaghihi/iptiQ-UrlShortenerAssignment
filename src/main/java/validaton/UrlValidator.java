package validaton;

import exception.SystemRuntimeException;

public class UrlValidator {
    public static UrlValidator INSTANCE = null;

    private UrlValidator() {
    }

    public void validateURL(String url) {
        org.apache.commons.validator.routines.UrlValidator urlValidator = new org.apache.commons.validator.routines.UrlValidator();
        if (!urlValidator.isValid(url)) {
            throw new SystemRuntimeException("Invalid url format");
        }
    }

    public static UrlValidator getInstance() {
        return INSTANCE = INSTANCE == null ? new UrlValidator() : INSTANCE;
    }
}
