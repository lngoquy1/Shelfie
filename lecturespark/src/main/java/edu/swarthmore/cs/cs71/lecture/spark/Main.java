package edu.swarthmore.cs.cs71.lecture.spark;

import spark.ModelAndView;
import spark.Service;
import spark.TemplateViewRoute;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

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
    private static TemplateViewRoute lightsOut = (request, response) -> {
        if (request.session().attribute("model") == null) {
            request.session().attribute("model", new LightsOutModelImpl());
        }
        LightsOutModel model = request.session().attribute("model");

        Map<String,Object> viewModel = new HashMap<>();
        viewModel.put("model", model);
        return new ModelAndView(viewModel, "/templates/lightsOut.vm");
    };

    public static void main(String[] args) {
        Service service = Service.ignite();
        service.port(34567);
        service.staticFiles.location("/static");

        service.get("/hello2",
                hello2,
                new VelocityTemplateEngine());
        service.get("/lightsOut",
                lightsOut,
                new VelocityTemplateEngine());

        service.init();
    }
}
