package edu.swarthmore.cs.cs71.shelved.common.junit.tests;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.simple.MessageBodyCreateUser;
import edu.swarthmore.cs.cs71.shelved.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.api.User;
import org.junit.Assert;
import org.junit.Test;

public class MessageBodyTests {
    @Test
    public void testMessageCreateUser(){
        User user = new SimpleUser();
        user.setSalt();
        user.setUserName("leah");
        user.setPassword("badpassword");
        user.setName("leah B");

        MessageBodyCreateUser message = new MessageBodyCreateUser();
        message.setUser(user);
        String json = message.createJSON();
        Gson gson = new Gson();
        Appendable output = new StringBuffer();
        gson.toJson(user, output);
        String jsonTest = output.toString();
        Assert.assertEquals(json, jsonTest);
    }
}
