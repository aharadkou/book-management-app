package com.epam.training.helpers;

import org.junit.Assert;
import org.junit.Test;


public class XSSDefenderTest {

    @Test
    public void getCleanStringTest() {
        XSSDefender defender = new XSSDefender();
        String[] badStrings = new String[] {
                "Hello<script>alert(1)</script>fffAawww",
                "<script>g</script>",
                "aawa<script>alert(Document.cookie)</script>",
                "<script>fwafwafa</script>fawfawfa<script>fwafawwwwwhrh</script>"
        };
        for(var badString : badStrings) {
            String actual = defender.getCleanString(badString);
            Assert.assertFalse(defender
                                .containsMaliciousScript(actual));
        }
    }

}
