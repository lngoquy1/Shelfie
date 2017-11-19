package edu.swarthmore.cs.cs71.shelved.model.spark;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUser;
import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import edu.swarthmore.cs.cs71.shelved.network.InvalidLoginUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.LoginUserResponse;
import edu.swarthmore.cs.cs71.shelved.network.ResponseMessage;
import org.hibernate.SessionFactory;
import spark.Request;
import spark.Response;

import javax.persistence.EntityManager;

public class ServerRouteLogin extends ServerRoute{

    public ServerRouteLogin(SessionFactory sf) {
        super(sf);
    }

    @Override
    protected ResponseMessage execute(Request request, Response response) {

        HibUserService service = new HibUserService();
        System.out.println("getting user id");
        int id = service.getUserNameId(getSf(),request.queryParams("email"));
        System.out.println("got user id");
        boolean result = service.checkUserValid(getSf(),
                id,
                request.queryParams("password")

        );

        if (result){
            return new LoginUserResponse(id);
        }

        return new InvalidLoginUserResponse("Invalid user login");
    }
}
