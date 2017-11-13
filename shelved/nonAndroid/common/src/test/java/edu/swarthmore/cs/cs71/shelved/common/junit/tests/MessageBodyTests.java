package edu.swarthmore.cs.cs71.shelved.common.junit.tests;
import com.google.gson.Gson;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleMessageBody;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import edu.swarthmore.cs.cs71.shelved.model.api.User;
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

        SimpleMessageBody message = new SimpleMessageBody();
        //message.setUser(user);
        String json = message.createJSON(user);
        System.out.println("message: " + json);
        Gson gson = new Gson();
        Appendable output = new StringBuffer();
        gson.toJson(user, output);
        String jsonTest = output.toString();
        Assert.assertEquals(json, jsonTest);
    }

    @Test
    public void testReadUserObject() {
        User user = new SimpleUser();
        user.setSalt();
        user.setUserName("leah");
        user.setPassword("badpassword");
        user.setName("leah B");

        SimpleMessageBody message = new SimpleMessageBody();
        //message.setUser(user);
        String json = message.createJSON(user);
        Gson gson = new Gson();
        Appendable output = new StringBuffer();
        gson.toJson(user, output);

        //User readUser = gson.fromJson(user, SimpleUser.class);


    }
}
