package bbs.model;

<<<<<<< HEAD
/**
 * 帖子实体对象
 * @author 王k
 *
 */
public class Invitation {

	private int invitaionId;
	private int author;
	private String title;
	private int isEssence;
	private String type;
	private String content;
	public int getInvitaionId() {
		return invitaionId;
	}
	public void setInvitaionId(int invitaionId) {
		this.invitaionId = invitaionId;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIsEssence() {
		return isEssence;
	}
	public void setIsEssence(int isEssence) {
		this.isEssence = isEssence;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
=======
import java.util.Date;

/**
 * Created by sjf on 5/24/17.
 */
public class Invitation {
    private int invitationId;
    private int author;
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

    public void setDateCreate(Date dateCreate) { this.dateCreate = dateCreate; }
>>>>>>> b0ce967299ea074fa6bcc240739d97f76fd383fc
}
