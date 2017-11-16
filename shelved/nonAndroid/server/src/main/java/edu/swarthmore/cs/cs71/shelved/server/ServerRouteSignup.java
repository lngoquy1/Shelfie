package edu.swarthmore.cs.cs71.shelved.server;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import org.hibernate.SessionFactory;
import spark.Request;

public class ServerRouteSignup extends ServerRoute {
    public ServerRouteSignup(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected Object execute(Request request) {
        return new HibUserService().createUser(
                request.queryParams("email"),
                request.queryParams("password")
        );
    }
}
