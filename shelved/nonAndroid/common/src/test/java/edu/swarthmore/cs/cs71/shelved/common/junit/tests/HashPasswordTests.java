package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.Login;
import edu.swarthmore.cs.cs71.shelved.User;
import org.junit.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashPasswordTests {

    @Test
    public void testPasswordHash() {
        Login login = new Login();
        User user = login.createUser("leah", "badpassword", "Leah Brumgard", "", "");
        String salt = user.getSalt();
        String password = user.getPassword();

        String enteredCorrectPw = "badpassword";
        String hashedCorrectEntered = BCrypt.hashpw(enteredCorrectPw, salt);

        String enteredWrongPw = "wrongpassword";
        String hashedWrongEntered = BCrypt.hashpw(enteredWrongPw, salt);


        Assert.assertEquals(password, hashedCorrectEntered);

        Assert.assertNotEquals(password, hashedWrongEntered);


    }
}
