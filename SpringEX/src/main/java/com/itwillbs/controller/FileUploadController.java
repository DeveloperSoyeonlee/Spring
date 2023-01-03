package com.itwillbs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	
	private static final String FILE_PATE="c:\\spring";
	private static final Logger mylog = LoggerFactory.getLogger(FileUploadController.class);
	
	//http://localhost:8080/upload
	/** 
	 * 파일업로드 뷰
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public void uploadForm() {
		mylog.debug(" upload.jsp 페이지로 이동 ");
	}
	
	
	/**
	 * 파일업로드 처리
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView uploadPro(MultipartHttpServletRequest multi) throws Exception {
		
		// 한글처리 
		multi.setCharacterEncoding("UTF-8");
		
		// 전달정보 저장
		Map map = new HashMap();
		
		// 전달된 파라미터의 이름들을 모두 저장 (이름 정보들을 빼서 보여줌)
		Enumeration enu = multi.getParameterNames(); 
					//Enumeration : 리스트가 아니여도 리스트같은 정보들을 저장하여 사용할 수 있게 해줌
					//getParameterNames : 파라미터가 여러개여서 그 이름만을 가져오는 메서드
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement(); //name 이라는 변수에 넣어주기 
//			mylog.debug("name : @@@@@@@@@@@@@@@@@@@@@@: " + name);
			String value = multi.getParameter(name);
			map.put(name, value);
		}
		
		mylog.debug("map : " + map);
		
		//------------------파일정보를 제외한 파라미터 정보 모두 저장 -----------------------
		
		// 파일정보 처리
		List<String>fileList = fileProcess(multi);
		map.put("fileList", fileList);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result"); // 뷰페이지 이동
		mav.addObject("map",map); // 객체정보를 위의 map 으로 보낼 것
		
		return mav;
		
	} // uploadPro
	
	
	
	/**
	 * 파일업로드시 전달되는 파일정보 처리 메서드
	 */
	private List<String> fileProcess(MultipartHttpServletRequest multi) throws Exception{
		mylog.debug(" 업로드 파일 처리 시작");
		
		// 업로드 파일명을 저장하는 리스트
		List<String> fileList = new ArrayList<String>();
		
		
		// 전달받은 파라미터(파일) 정보 저장
		Iterator<String> fileNames // Iterator : Enumeration와 유사. getFileNames를 Iterator형태로 저장되면 반복문안에서 실행할 수 있는 형태로 만듦
						= multi.getFileNames(); //getFileNames : getParameterNames 와 비슷한 역할.
		while(fileNames.hasNext()) {
			String fileName = fileNames.next(); //커서를 이동하면서 
//			mylog.debug("fileName : "+ fileName);
			// 전달받은 파일의 정보를 저장(데이터)
			MultipartFile mFile = multi.getFile(fileName); // 파일의 내용 저장
			// 전달받은 파일의 이름정보를 저장
			String oFilenName = mFile.getOriginalFilename(); // 파일의 이름도 저장
			
			
			fileList.add(oFilenName);
			
			// 업로드 처리 C:\\spring
			File file = new File(FILE_PATE+"\\"+fileName); 
			
			if(mFile.getSize() != 0) { // 업로드된 파일정보가 있으면 
				if(!file.exists()) { // 파일이 있는 경로에 기존에 업로드 폴더가 존재하는지 체크하는 것.
					
					if(file.getParentFile().mkdir()) {
						// 부모역할을 하는 폴더를 생성
						file.createNewFile();
						// 임시파일 생성
						
					}
				}
			}
//			file.createNewFile(); // 파일이름생성
			// 정보가 없으면 파라미터로 변환해서 사용할 수 있도록
			mFile.transferTo(new File(FILE_PATE+"\\"+oFilenName)); //mFile : 파일 내용 저장한 것
			// -> 임시저장 파일의 정보를 업로드된 실제파일로 변경
		}
		
		mylog.debug(" 업로드 파일 처리 끝");
		
		return fileList;
	}
	
	
	
	/**
	 * 다운로드
	 */
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void download(@RequestParam("fileName")String fileName, 
						HttpServletResponse response) throws Exception { // 입출력의 형태 (java 개념 多필요)
		
		// 1. 파일출력 통로를 생성
		OutputStream out = response.getOutputStream(); // 원래 response 로 http로 보내주는데 그 통로를 만들어주는것
		
		// 2. 다운로드 파일의 위치 (== 업로드 파일의 위치)
		String downFile = FILE_PATE+"\\"+fileName;
		mylog.debug(downFile);
		
		// 3. 다운로드
		File file = new File(downFile);
		
		response.setHeader("Cache-control", "no-cache"); // 기존에 들어있는 설정값 변경
		response.addHeader("Content-disposition", "attachment; fileName="+fileName); // 설정값 추가
		// -> 모든 파일이 다운로드 형태로 처리
		
		FileInputStream fis = new FileInputStream(file); // 위의 파일을 불러올 것임...
		byte[] buffer = new byte[1024*8]; // 8KB 준비
		
		// 방법 1
		while(true) {
			int count = fis.read(buffer); // FileInputStream을 통해, buffer을 통해( 8KB씩) 읽는다.
			if(count == -1) break; // 파일의 끝 (end of file)  모든 파일의 끝에는 -1 이 숨어있다. 
			
			out.write(buffer,0,count); // buffer을 사용하여 count배열을 사용하여 읽은만큼 출력해내겠다
		}
		
		out.flush(); // 버퍼의 빈공간을 공백으로 채우기
		
		fis.close();
		out.close();
	
		// 방법 2
//		int count = 0;
//		while( (count = fis.read(buffer))!= -1) {
//			out.write(buffer,0,count);
//		}
				
		
		//
	}
	
	
	
}
