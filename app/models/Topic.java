package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Dmytro_Veres on 7/9/2014.
 */
@Entity
public class Topic extends Model {

    public static final int MAX_TOPIC_NAME_LENGTH = 100;
    public static final int MAX_SUBJECT_LENGTH = 50;

    @Id
    private Long topicId;

    @Constraints.Required
    @Constraints.MaxLength(value = MAX_TOPIC_NAME_LENGTH)
    private String topicName;

    @Constraints.MaxLength(value = MAX_SUBJECT_LENGTH)
    private String topicSubject;

    @Constraints.Required
    private String topicComment;

    @Constraints.Required
    private Date creationDate;

    public Topic() {

    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicSubject() {
        return topicSubject;
    }

    public void setTopicSubject(String topicSubject) {
        this.topicSubject = topicSubject;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTopicComment() {
        return topicComment;
    }

    public void setTopicComment(String topicComment) {
        this.topicComment = topicComment;
    }
}
