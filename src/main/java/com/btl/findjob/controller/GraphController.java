package com.btl.findjob.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.btl.findjob.model.PersonInfoGraphDTO;
import com.btl.findjob.service.PersonGraphService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class GraphController {

	@Inject
	PersonGraphService personGraphService;

	public String JsonData(PersonInfoGraphDTO pigdto) {
		// json �ٲٱ� ���� mapper ����
		ObjectMapper mapper = new ObjectMapper();
		// ������ ���� ��� ���� tmp�� ����
		List tmp = personGraphService.personGetGraph(pigdto.getCi_companyName());
		// ����� ����
		String jsonInString="";
		
		try {
			 jsonInString = mapper.writeValueAsString(tmp);
			// ��� list�� json���� ��ȯ
			// System.out.println(jsonInString);
			// json ����� jsp�� �ѱ��
			// req.getWriter().print(jsonInString);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonInString;
	}

	@RequestMapping(value = "/info")
	public String infoController(Model model,PersonInfoGraphDTO pigdto) {
		System.out.println("infoController: "+JsonData(pigdto));
		model.addAttribute("ci_companyName", pigdto.getCi_companyName());
		return "/info";
	}

	@RequestMapping(value = "/test")
	public @ResponseBody List AjaxView(HttpServletResponse req, PersonInfoGraphDTO pigdto) {
		List tmp = personGraphService.personGetGraph(pigdto.getCi_companyName());
		System.out.println("ResponseBody :"+tmp);
		return tmp;
	}
}
