package bbs.model;

/**
 * Created by sjf on 5/24/17.
 */
public class Comment {
    private int commentId;
    private int commentUser;
    private int invitation;
    private int answerUser;
    private String content;

    public Comment(int commentUser, int invitation, int answerUser, String content) {
        super();
        this.commentUser = commentUser;
        this.answerUser = answerUser;
        this.invitation = invitation;
        this.content = content;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getCommentUser() {
        return commentUser;
    }

    public int getAnswerUser() {
        return answerUser;
    }

    public int getInvitation() {
        return invitation;
    }

    public String getContent() {
        return content;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setCommentUser(int commentUser) {
        this.commentUser = commentUser;
    }

    public void setAnswerUser(int answerUser) {
        this.answerUser = answerUser;
    }

    public void setInvitation(int invitation) {
        this.invitation = invitation;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
