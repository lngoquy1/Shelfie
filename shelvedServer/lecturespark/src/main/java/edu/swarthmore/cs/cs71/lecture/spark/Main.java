package edu.swarthmore.cs.cs71.lecture.spark;

import jdk.nashorn.api.scripting.JSObject;
import spark.ModelAndView;
import spark.Service;
import spark.TemplateViewRoute;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.route.HttpMethod.get;

public class Main {
    private static final TemplateViewRoute hello2 = (request, response) ->
    {
        int number;
        if (request.session().attribute("number") == null) {
            number = 0;
        } else {
            number = request.session().attribute("number");
        }
        number++;
        request.session().attribute("number", number);

        Map<String, String> viewModel = new HashMap<>();
        viewModel.put("x", String.valueOf(number));

        return new ModelAndView(viewModel, "/templates/page.vm");
    };
    private static TemplateViewRoute getBooks = (request, response) -> {
        if (request.session().attribute("shelf") == null) {
            request.session().attribute("shelf", new Shelf());
        }
        Shelf shelf = request.session().attribute("shelf");
        Map<String,Object> viewShelf = new HashMap<>();
        viewShelf.put("shelf", shelf);
        return new ModelAndView(viewShelf, "/templates/shelf.vm");
    };


    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(25297);
        service.staticFiles.location("/static");

        service.get("/hello2",
                hello2,
                new VelocityTemplateEngine());
        service.get("/getBooks",
                getBooks,
                new VelocityTemplateEngine());

        service.init();
    }
}
