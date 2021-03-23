package com.epam.training.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XSSDefender {

    private static final Pattern XSS_PATTERN
            = Pattern.compile("<script>.*</script>");

    /**
     * Cleans string from scripts.
     * @param rawText checked string
     * @return string without scripts
     */
    public String getCleanString(final String rawText) {
        return XSS_PATTERN
                    .matcher(rawText)
                        .replaceAll("");
    }

    /**
     * Checks string for XSS.
     * @param text checked string
     * @return true if string contains malicious script
     * injection, false other way
     */
    public boolean containsMaliciousScript(String text) {
        Matcher matcher = XSS_PATTERN.matcher(text);
        return matcher.find();
    }

}
