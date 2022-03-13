package com.yoonveloping.apitest.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.yoonveloping.apitest.papago.PapagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

	private final PapagoService papagoService;

	@Autowired
	public WebController(PapagoService papagoService) {
		this.papagoService = papagoService;
	}

	@GetMapping("/translate")
	public String translate(Model model, @RequestParam(required = false) String originalText) {
		final JsonNode text = papagoService.post(originalText);

		model.addAttribute("text", originalText);
		System.out.println("originalText = " + originalText);

		final String translatedText = papagoService.parsing(text);
		model.addAttribute("translatedText", translatedText);
		return "translate";
	}
}
