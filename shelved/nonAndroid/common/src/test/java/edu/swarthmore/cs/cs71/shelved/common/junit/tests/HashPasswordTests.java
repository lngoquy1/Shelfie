package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import edu.swarthmore.cs.cs71.shelved.Login;
import edu.swarthmore.cs.cs71.shelved.User;
import org.junit.Test;

public class HashPasswordTests {

    @Test
    public void testPasswordHash() {
        Login login = new Login();
        User user = login.createUser("leah", "badpassword", "Leah Brumgard", "", "");
        String salt = user.getSalt();
        System.out.println("Salt: " + salt);

        String password = user.getPassword();
        System.out.println("Password: " + password);
    }
}
