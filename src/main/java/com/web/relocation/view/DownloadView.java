package com.web.relocation.view;/*
package com.web.view;

import com.firstweb.controller.BoardController;
import com.firstweb.entity.BoardAttachEntity;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.util.Map;

public class DownloadView extends AbstractView {
//public class DownloadView implements View {

	*/
/*@Override
	protected void renderMergedOutputModel(
			Map<String, Object> params, //Controller에서 Model에 저장한 데이터
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardAttachEntity attach = (BoardAttachEntity)params.get("attach");
		
		//다운로드 처리
		//1. 브라우저에게 처리할 수 없는 컨텐츠로 알려주어서 다운로드 처리하도록 지정
		//response.setContentType("text/html");
		//response.setContentType("image/png");
		response.setContentType("application/octet-stream");

		//2. 다운로드 처리할 때 필요한 정보 제공
		response.addHeader(
			"Content-Disposition", 
			"Attachment;Filename=\"" + new String(attach.getOriFileName().getBytes("utf-8"), "ISO-8859-1") + "\"");

		//3. 파일을 응답스트림에 기록
		String file2 = 
			request.getServletContext().getRealPath("/WEB-INF/board-upload/" + attach.getServerFileName());
		if (file2 == null) {
			file2 = BoardController.dockerPath + "WEB-INF/board-upload/" + attach.getServerFileName();
		}
		BufferedInputStream istream = new BufferedInputStream(new FileInputStream(file2));
		BufferedOutputStream ostream = new BufferedOutputStream(response.getOutputStream());
		while (true) {
			int data = istream.read();
			if (data != -1) 
				ostream.write(data);
			else
				break;
		}
		istream.close();
		ostream.close();
	}*//*


}
*/
