package bbs.model;

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
}
