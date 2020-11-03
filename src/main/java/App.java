import DAO.Sql2oGeneralNews;
import models.GeneralNews;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        System.out.println("Getting ready!");

        String connectionString = "jdbc:postgresql://localhost:5432/news";

        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        Sql2oGeneralNews sql2oGeneralNews = new Sql2oGeneralNews(sql2o);

        Map<String, Object> model = new HashMap<>();

        get("/", (request, response) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/generalNews", (request, response) -> {
            model.put("generalNews", sql2oGeneralNews.getAllGeneralNews());
            return new ModelAndView(model, "generalnews.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
