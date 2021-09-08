package service.paging;

public class pagingService {

	public static paging getPage(int pageNo, int pageSize){
		
		paging page = new paging();
		
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		
		return page;
	}
	
}
