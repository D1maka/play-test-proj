package controllers;

import models.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

//    public static Result getIndex() {
//        return ok(index.render("Your new application is ready."));
//    }

    public static Result crateTopic() {
        Form<Topic> newTopic = Form.form(Topic.class);
        return ok(createtopic.render("azaza", newTopic));
    }
}
