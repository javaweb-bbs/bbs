package bbs.util;
import java.util.List;
public class PageBean {
	private List list;
	private int pageSize;
	private int pageNo;
	private int totalRecords;
	
	
	public int getTotalPages(int totalRecords,int pageSize){
		if(totalRecords%pageSize==0){
			return totalRecords/pageSize;
		}else
		{
			return totalRecords/pageSize+1;
		}
	}
	
	public int getTopPageNo(){
		return 1;
	}
	
	public int getPreviousPageNo(){
		if(pageNo<=1){
			return 1;
		}
		return pageNo-1;
		
	}
	
	public int getBottomPageNo(){
		return getTotalPages(pageNo, pageNo);
	}
	
	public int getNextPageNo(){
		if(pageNo>=getBottomPageNo())
			return getBottomPageNo();
		return pageNo+1;
		
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
