package com.itwillbs.domain;

public class PageVO {
	
	// 하단부 페이징처리에 필요한 객체 정보
	// startPage, endPage, prev, next
	// totalCount(DB)
	// Cri - [ page정보, perPageNum(몇개씩 출력할건지) ] 한덩어리로 넣어놨썰 
	// displayPageNum (페이지블럭 개수)
	
	
	// 변수들 만들어보자고
	private int startPage;
	private int endPage;
	private int totalCount; // 계산하진않지만 가져와야하므로
	private boolean prev;
	private boolean next;
	
	private Criteria cri; // 객체주입할 수 있게 레퍼런스 준비
	
	private int displayPageNum = 10;

	
	
	
	
	
//-------------------------------------------------------------------------	
	
	// 게터세터  (setCri, setTotalCount 두개만 위로 올리기)
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	//메서드 하나 새로 생성 (데이터 계산)
	public void calcData() {
		// 페이징처리 하단부에 필요한 정보를 계산하는 메서드
		System.out.println(" calcData() - 시작");
		
		// endPage
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		
		// startPage
		startPage
		= (endPage - displayPageNum) + 1;
		
		
		// totalCount(DB) 계산하기 (이걸 위해 end페이지 정확한 값을 계산해야함)
		// end페이지 값 바뀌어야하는 이유 : 토탈카운트에 따라 end페이지값이 달라지므로 ...?
		// endPage ( 전체 글 개수가 endPage만큼 없을 경우)
		// 전체 글 개수에 맞는 페이지 수 계산
		int tmpEndPage = (int)Math.ceil((totalCount/(double)cri.getPerPageNum()));
		
		if(endPage > tmpEndPage) {
			endPage = tmpEndPage;
		}
		
		// prev
		prev = (startPage == 1)? false : true;
		
		// next
		next = (endPage * cri.getPerPageNum()) >= totalCount? false : true;
		
		System.out.println(" calcData() - 끝 (endPage출력) ");
		
		System.out.println(" endPage : " + endPage);
		System.out.println(" startPage : " + startPage);
		System.out.println(" prev : " + prev);
		System.out.println(" next : " + next);
		
		System.out.println(" totalCount : " + totalCount);
		System.out.println(" tmpEndPage : " + tmpEndPage);

	} // calcData()
	
	
	
	
	
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public Criteria getCri() {
		return cri;
	}


	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	
	
	
	
	//toString
	@Override
	public String toString() {
		return "pageVO [startPage=" + startPage + ", endPage=" + endPage + ", totalCount=" + totalCount + ", prev="
				+ prev + ", next=" + next + ", cri=" + cri + ", displayPageNum=" + displayPageNum + "]";
	}
	
	
	
	
	
	
	
}	
