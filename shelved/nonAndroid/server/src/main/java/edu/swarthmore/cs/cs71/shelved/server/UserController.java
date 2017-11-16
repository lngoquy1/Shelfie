package edu.swarthmore.cs.cs71.shelved.server;

import edu.swarthmore.cs.cs71.shelved.model.server.HibUserService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;


import static edu.swarthmore.cs.cs71.shelved.server.JsonUtil.json;
import static spark.Spark.post;


public class UserController {
    public UserController(final HibUserService userService) {
        post("/signup", (Request request, Response response) -> {
            return userService.createUser(
                    request.queryParams("email"),
                    request.queryParams("password"));

        },json());
    }

}
