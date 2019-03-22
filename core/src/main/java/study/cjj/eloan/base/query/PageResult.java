package study.cjj.eloan.base.query;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页查询的结果对象
 */
@Setter
@Getter
public class PageResult {

	private List listData ;  //当前页的结果集数据
	private Integer totalCount ;	//结果总数
	
	private Integer currentPage  = 1;
	private Integer pageSize  = 10 ;
	
	private Integer prevPage  ;	//上一页
	private Integer nextPage  ;	//下一页
	private Integer totalPage  ;	//总页数
	
	//如果总数据条数为0 返回一个空集 
	public static PageResult empty(Integer pageSize){
		return new PageResult(new ArrayList<>(), 0, 1, pageSize);
	}
	
	//如果totalPage为0 返回的页数为第一页
	public int getTotalPage(){
		return  totalPage == 0 ? 1 : totalPage ;
	}	

	public PageResult(List listData, Integer totalCount, Integer currentPage,
			Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		
		//计算数据
		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize :  this.totalCount/ this.pageSize + 1 ;
	
		this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage -1 :1 ;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;  
	}
	
}
