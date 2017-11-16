package edu.swarthmore.cs.cs71.shelved.server;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import org.hibernate.SessionFactory;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

public class ServerRouteSignup extends ServerRoute {
    public ServerRouteSignup(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected List<Object> execute(Request request) {
        List<Object> out = new ArrayList<>();
        out.add(new HibUserService().createUser(
                request.queryParams("email"),
                request.queryParams("name"),
                request.queryParams("password")));
        return out;
    }
}
