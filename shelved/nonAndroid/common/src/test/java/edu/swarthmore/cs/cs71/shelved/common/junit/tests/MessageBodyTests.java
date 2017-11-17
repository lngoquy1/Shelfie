package edu.swarthmore.cs.cs71.shelved.common.junit.tests;

import com.google.gson.reflect.TypeToken;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleMessageBody;
import edu.swarthmore.cs.cs71.shelved.model.simple.SimpleUser;
import org.junit.Test;

import java.lang.reflect.Type;

public class MessageBodyTests {
    @Test
    public void testMessageCreateUser(){
        SimpleUser user = new SimpleUser();
        user.setSalt();
        user.setUserName("leah");
        user.setPassword("badpassword");
        user.setName("leah B");

        SimpleMessageBody message = new SimpleMessageBody();
        //message.setUser(user);
        String json = message.createJSON(user);
        System.out.println("message: " + json);
        Type typeOfT = new TypeToken<SimpleUser>() { }.getType();

        // Test json -> object

        SimpleUser userFromJSON = (SimpleUser)(message.objectFromJSON(json, typeOfT));
//        SimpleUser userFromJSON = (SimpleUser) object;

//        Assert.assertEquals(userFromJSON.header, user.header);
//        Assert.assertEquals(userFromJSON.getPassword(), user.getPassword());
//        Assert.assertEquals(userFromJSON.getSalt(), user.getSalt());
//        Assert.assertEquals(userFromJSON.getBio(), user.getBio());
//        Assert.assertEquals(userFromJSON.getLocation(), user.getLocation());
//        Assert.assertEquals(userFromJSON.getName(), user.getName());
//        Assert.assertEquals(userFromJSON.getUserName().getUserName(), user.getUserName().getUserName());



    }

//    @Test
//    public void testReadUserObject() {
//        SimpleUser user = new SimpleUser();
//        user.setSalt();
//        user.setUserName("leah");
//        user.setPassword("badpassword");
//        user.setName("leah B");

//        Gson gson = new Gson();
//        String userJsonString = gson.toJson(user);
//        Type typeOfT = new TypeToken<SimpleUser>(){}.getType();
//        SimpleUser firstJsonNowUser = gson.fromJson(userJsonString, typeOfT);

//        SimpleMessageBody message = new SimpleMessageBody();
//        message.objectFromJSON()
//
//
//        Assert.assertEquals(firstJsonNowUser.header, user.header);
//        Assert.assertEquals(firstJsonNowUser.getPassword(), user.getPassword());
//        Assert.assertEquals(firstJsonNowUser.getSalt(), user.getSalt());
//        Assert.assertEquals(firstJsonNowUser.getBio(), user.getBio());
//        Assert.assertEquals(firstJsonNowUser.getLocation(), user.getLocation());
//        Assert.assertEquals(firstJsonNowUser.getName(), user.getName());
//        Assert.assertEquals(firstJsonNowUser.getUserName().getUserName(), user.getUserName().getUserName());




//    }
}
