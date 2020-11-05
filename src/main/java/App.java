import DAO.Sql2oDepartment;
import DAO.Sql2oDepartmentNews;
import DAO.Sql2oGeneralNews;
import DAO.Sql2oUser;
import models.Department;
import models.DepartmentNews;
import models.GeneralNews;
import models.User;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {
        System.out.println("Getting ready!");

        String connectionString = "jdbc:postgresql://localhost:5432/news";


        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        Sql2oGeneralNews sql2oGeneralNews = new Sql2oGeneralNews(sql2o);
        Sql2oDepartment sql2oDepartment = new Sql2oDepartment(sql2o);
        Sql2oDepartmentNews sql2oDepartmentNews = new Sql2oDepartmentNews(sql2o);
        Sql2oUser sql2oUser = new Sql2oUser(sql2o);

        Map<String, Object> model = new HashMap<>();

        get("/", (request, response) -> {
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//        get("/generalNews", (request, response) -> {
//            model.put("generalNews", sql2oGeneralNews.getAllGeneralNews());
//            return new ModelAndView(model, "generalnews.hbs");
//        }, new HandlebarsTemplateEngine());

        get("/add-generalNews", (request, response) -> {
            model.put("generalNews", sql2oGeneralNews.getAllGeneralNews());
            return new ModelAndView(model, "general-news-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/add-generalNews",(req,res)->{
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            GeneralNews generalNews = new GeneralNews(title, content);
            sql2oGeneralNews.add(generalNews);
            model.put("generalNews", sql2oGeneralNews.getAllGeneralNews());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add-users", (request, response) -> {
            model.put("users", sql2oUser.getAllUser());
            return new ModelAndView(model, "user-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/add-users",(req,res)->{
            String name = req.queryParams("name");
            String position = req.queryParams("position");
            String role = req.queryParams("role");
            int departmentId = Integer.parseInt(req.params("department"));
            User newUser = new User(name,position,role,departmentId);
            sql2oUser.add(newUser);
            model.put("users", sql2oUser.getAllUser());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add-department", (request, response) -> {
            model.put("departments", sql2oUser.getAllUser());
            return new ModelAndView(model, "department-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/add-department",(req,res)->{
            String name = req.queryParams("name");
            String description = req.queryParams("description");
            Department department = new Department(name, description);
            sql2oDepartment.add(department);
            model.put("department",sql2oDepartment.getAllDepartments() );
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add-departmentNews", (request, response) -> {
            model.put("departments", sql2oUser.getAllUser());
            return new ModelAndView(model, "department-news-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/add-departmentNews",(req,res)->{
            String title = req.queryParams("title");
            String content = req.queryParams("content");
            int departmentId = Integer.parseInt(req.queryParams("content"));
            DepartmentNews departmentNews = new DepartmentNews(title,content,departmentId);
            sql2oDepartmentNews.add(departmentNews);
            model.put("department",sql2oDepartmentNews.getAllDepartmentNews());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
