package com.itwillbs.domain;

public class Criteria {
	
	// 페이징처리에 필요한 정보를 저장 (크게 두가지 1)페이지 시작정보 2)사이즈) 
	
	private int page; // 시작페이지 정보
	private int perPageNum; // 페이지 개수 
	
	
	
	// 페이징처리 정보가 없을 경우 사용할 기본값 설정 
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	
	
	// 페이지정보 (음수일 때 1페이지 정보)
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return; // 리턴을해야 밑에서 재설정하지 않으므로.. 그렇지 않으면 -1 넣었을때 -1나옴
		}
		
		this.page = page;
	}
	
	
	
	// 페이지 사이즈 (음수거나 페이지수 100개 이상일때 => 기본값 10개씩으로 변경)
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum; // 위가 아니면 전달받은 페이지넘형태로 출력하기
	}

	
	
	// Getter Setter 
	public int getPage() {
		return page;
	}

	// #{pageStart} 도 필요하니까 위의 getPage 와 이름을 같게 해주는 작업이 필요
	// 이이름을 전달못하면 이이름과 같은 메서드를 만들면 되는 것 !!
	//  #{pageStart} 안에 정보를 전달
	public int getPageStart() {
//		return page; // 그냥 바로 이렇게 안준다 ~
		// page = (page
		return (this.page-1)*perPageNum; 
		
	}
	
	
	// #{perPageNum} 안에 정보를 저장
	public int getPerPageNum() {
		return perPageNum;
	}



	// alt shift s + s
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
	
}
