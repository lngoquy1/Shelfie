package edu.swarthmore.cs.cs71.shelved.model.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUser;
import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

public class ServerRouteSignup extends ServerRoute {
    public ServerRouteSignup(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected Object execute(Request request, Response response) {
        HibUser newUser = new HibUserService().createUser(
                request.queryParams("email"),
                request.queryParams("name"),
                request.queryParams("password"));
        return newUser;
    }
}
