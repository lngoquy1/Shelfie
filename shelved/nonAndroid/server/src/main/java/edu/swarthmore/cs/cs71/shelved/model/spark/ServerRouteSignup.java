package edu.swarthmore.cs.cs71.shelved.model.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUser;
import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

public class ServerRouteSignup extends ServerRoute {
    public ServerRouteSignup(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {
        HibUser newUser = new HibUserService().createUser(
                request.queryParams("email"),
                request.queryParams("name"),
                request.queryParams("password"));

        boolean error = false;
        return new ResponseMessage(error, newUser);
    }
}
