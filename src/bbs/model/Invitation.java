package bbs.model;

import java.util.Date;


public class Invitation {
    private int invitationId;
    private int author;
    private String authorName;
    private String title;
    private String content;
    private Boolean isEssence;
    private String type;
    private Date dateCreate;

    public Invitation() {
        super();
    }

    public Invitation(String title, String content, Boolean isEssence, String type, int author) {
        super();
        this.title = title;
        this.content = content;
        this.author = author;
        this.type = type;
        this.isEssence = isEssence;
    }

    public int getInvitationId() {
        return invitationId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getEssence() {
        return isEssence;
    }

    public int getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public Date getDateCreate() { return dateCreate; }

    public String getAuthorName() {
        return authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setEssence(Boolean essence) {
        isEssence = essence;
    } 

    public void setInvitationId(int invitationId) {
        this.invitationId = invitationId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDateCreate(Date dateCreate) { 
    	this.dateCreate = dateCreate; 
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
