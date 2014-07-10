package controllers;

import models.Topic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.ArrayList;
import java.util.List;

public class Application extends Controller {

    public static List<Topic> topicsList = new ArrayList<>();

    public static Result getTopics() {
//        List<Topic> existingTopics = Topic.find.all();
        return ok(topics.render(topicsList));
    }

    public static Result newTopic() {
//        return redirect("/topics/new");
        Form<Topic> form = Form.form(Topic.class);
        return ok(createtopic.render(form));
    }

    public static Result createNewTopic() {
        Form<Topic> form = Form.form(Topic.class).bindFromRequest();

        Topic newTopic = form.get();
        System.out.println(newTopic);
        topicsList.add(newTopic);

//        List<Topic> existingTopics = Topic.find.all();
        return redirect("/topics");
    }
}
