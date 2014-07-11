package models;

import com.avaje.ebean.Ebean;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Dmytro_Veres on 7/9/2014.
 */
@Entity
public class Comment extends Model {
    @Id
    public Long commentId;

    @Constraints.Required
    @ManyToOne(targetEntity = Topic.class)
    public Topic topic;

    @Constraints.Required
    public String commentText;

    @Constraints.Required
    public Date commentDate;

    public Comment() {
    }

    public static Finder<Long, Comment> find = new Finder<Long, Comment>(Long.class, Comment.class);

    public static void store(Comment comment) {
        Ebean.save(comment);
    }
}
