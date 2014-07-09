package controllers;

import models.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {

    public static Result createTopic() {
        Form<Topic> newTopic = Form.form(Topic.class);
        return ok(createtopic.render(newTopic));
    }

    public static Result createNewTopic() {
        Form<Topic> form = Form.form(Topic.class).bindFromRequest();
        return ok(createtopic.render(form));
    }
}
