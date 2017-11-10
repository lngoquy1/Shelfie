package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.User;
import org.junit.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class HashPasswordTests {

    @Test
    public void testPasswordHash() {
        SimpleUser user = new SimpleUser();
        user.setSalt();
        user.setUserName("leah");
        user.setPassword("badpassword");
        String salt = user.getSalt();
        String password = user.getPassword();

        String enteredCorrectPw = "badpassword";
        String hashedCorrectEntered = BCrypt.hashpw(enteredCorrectPw, salt);

        String enteredWrongPw = "wrongpassword";
        String hashedWrongEntered = BCrypt.hashpw(enteredWrongPw, salt);


        Assert.assertEquals(password, hashedCorrectEntered);

        Assert.assertNotEquals(password, hashedWrongEntered);
    }

    @Test
    public void testChangePassword() {
        SimpleUser user = new SimpleUser();
        user.setSalt();
        user.setUserName("leah");
        user.setPassword("badpassword");
        String salt = user.getSalt();
        String password = user.getPassword();

        System.out.println("user pw before change: " + user.getPassword());
        System.out.println("user salt before change: " + user.getSalt());

        System.out.println("badpassword hashed: " + BCrypt.hashpw("badpassword", user.getSalt()));

        user.changePassword("badpassword", "newpassword");

        System.out.println("user password after change: " + user.getPassword());
        System.out.println("user salt after change: " + user.getSalt());

        String newPw = "newpassword";
        String newSalt = user.getSalt();

        String hashedNewPw = BCrypt.hashpw(newPw, newSalt);

        Assert.assertEquals(hashedNewPw, user.getPassword());
        Assert.assertNotEquals(password, user.getPassword());
    }
}
