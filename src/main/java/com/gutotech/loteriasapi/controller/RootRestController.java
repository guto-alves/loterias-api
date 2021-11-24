package com.gutotech.loteriasapi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootRestController {

	@RequestMapping(value = "/")
	public void redirectToSwagger(HttpServletResponse response) throws IOException {
		response.sendRedirect("/swagger-ui/");
	}

}
