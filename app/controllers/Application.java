package controllers;

import models.Comment;
import models.Topic;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results.AsyncResult;

import scala.concurrent.Promise;
import views.html.*;

import java.util.Date;
import java.util.List;

public class Application extends Controller {

    public static F.Promise<Result> getTopics() {
        F.Promise<List<Topic>> promisedTopics = F.Promise.promise(
                new F.Function0<List<Topic>>() {
                    @Override
                    public List<Topic> apply() throws Throwable {
                        List<Topic> topics =  Topic.find.all();
                        return topics;
                    }
                }
        );

        return promisedTopics.map(new F.Function<List<Topic>, Result>() {
            @Override
            public Result apply(List<Topic> topics) throws Throwable {
                return ok(views.html.topics.render(topics));
            }
        });
    }

    public static Result newTopic() {
        Form<NewTopicData> form = Form.form(NewTopicData.class);
        return ok(createtopic.render(form));
    }

    public static F.Promise<Result> createNewTopic() {
        final Form<NewTopicData> form = Form.form(NewTopicData.class).bindFromRequest();

        if (form.hasErrors()) {
            return F.Promise.promise(new F.Function0<Result>() {
                @Override
                public Result apply() throws Throwable {
                    return badRequest(createtopic.render(form));
                }
            });
        } else {
            return F.Promise.promise(new F.Function0<Result>() {
                @Override
                public Result apply() throws Throwable {
                    NewTopicData newTopicData = form.get();
                    Topic newTopic = new Topic();
                    newTopic.topicName = newTopicData.topicName;
                    newTopic.topicSubject = newTopicData.topicSubject;
                    newTopic.topicComment = newTopicData.topicComment;
                    newTopic.creationDate = new Date();

                    Topic.store(newTopic);

                    return redirect("/topics");
                }
            });
        }
    }

    public static F.Promise<Result> topicPage(final Long id) {
        F.Promise<Result> resultPromise = F.Promise.promise(new F.Function0<Result>() {
            @Override
            public Result apply() throws Throwable {
                Topic topicData = Topic.find.byId(id);
                Form<NewCommentData> newCommentDataForm = Form.form(NewCommentData.class);
                List<Comment> comments = Topic.getTopicComments(topicData);
                return ok(topicPage.render(topicData, comments, newCommentDataForm));
            }
        });
        return resultPromise;
    }

    public static F.Promise<Result> newComment(Long topicId) {
        Form<NewCommentData> commentForm = Form.form(NewCommentData.class).bindFromRequest();
        if (commentForm.hasErrors()) {
            return F.Promise.promise(new F.Function0<Result>() {
                @Override
                public Result apply() throws Throwable {
                    return badRequest();
                }
            });
        } else {
            NewCommentData commentData = commentForm.get();
            Comment newComment = new Comment();
            Topic topic = Topic.find.byId(topicId);

            newComment.commentText = commentData.comment;
            newComment.topic = topic;
            newComment.commentDate = new Date();
            Form<NewCommentData> newCommentDataForm = Form.form(NewCommentData.class);

            Comment.store(newComment);
            return topicPage(topic.topicId);
        }

    }

    public static class NewTopicData {
        public static final int MAX_TOPIC_NAME_LENGTH = 100;
        public static final int MAX_SUBJECT_LENGTH = 50;

        @Constraints.Required
        @Constraints.MaxLength(value = MAX_TOPIC_NAME_LENGTH)
        public String topicName;

        @Constraints.Required
        @Constraints.MaxLength(value = MAX_SUBJECT_LENGTH)
        public String topicSubject;

        @Constraints.Required
        public String topicComment;

        public NewTopicData() {
        }
    }

    public static class NewCommentData {
        public static final int MAX_COMMENT_LENGTH = 300;

        @Constraints.Required
        @Constraints.MaxLength(value = MAX_COMMENT_LENGTH)
        public String comment;

        public NewCommentData() {
        }
    }
}
