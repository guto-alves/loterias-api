package com.gutotech.loteriasapi.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.ResultMatcher.matchAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
class ApiRestControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetLotteries() throws Exception {
		mockMvc.perform(get("/api"))
			.andDo(print())
			.andExpect(matchAll(
				status().isOk(),
				jsonPath("$", hasSize(10)),
				jsonPath("$.[1]").value("megasena"))
			);
	}
	
	@Test
	void testGetResultByIdSuccess() throws Exception {
		mockMvc.perform(get("/api/megasena/1"))
			.andDo(print())
			.andExpect(matchAll(
				status().isOk(),
				jsonPath("loteria").value("megasena"),
				jsonPath("concurso").value(1),
				jsonPath("dezenas", hasSize(6)),
				jsonPath("timeCoracao").doesNotExist())
			);
	}

}
