package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmytro_Veres on 7/9/2014.
 */
@Entity
public class Topic extends Model {

    public static final int MAX_TOPIC_NAME_LENGTH = 100;
    public static final int MAX_SUBJECT_LENGTH = 50;

    @Id
    public Long topicId;

    @Constraints.Required(message = "This field is required")
    @Constraints.MaxLength(value = MAX_TOPIC_NAME_LENGTH)
    public String topicName;

    @Constraints.MaxLength(value = MAX_SUBJECT_LENGTH)
    public String topicSubject;

    @Constraints.Required
    public String topicComment;

    @Constraints.Required
    public Date creationDate;

    public Topic() {

    }

    public static Finder<Long, Topic> find = new Finder<Long, Topic>(Long.class, Topic.class);

    public static void store(Topic topic) {
        Ebean.save(topic);
    }

    public static List<Comment> getTopicComments(Topic topic) {
        ExpressionList<Comment> commentsExpression = Comment.find.where().eq("topic", topic);
        List<Comment> comments = new ArrayList<>();
        if (commentsExpression != null) {
            comments = commentsExpression.orderBy("commentDate desc").findList();
        }
        return comments;
    }

    public static boolean isUnique(String topicName) {
        List<Topic> topics = find.where().eq("topicsName", topicName).findList();
        return topics.size() == 0;
    }
}
