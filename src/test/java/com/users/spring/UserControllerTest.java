package com.users.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.spring.controller.UserController;
import com.users.spring.entity.User;
import com.users.spring.service.UserService;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Test User Creation Process")
	public void testSave() throws Exception {
		// GIVEN
		User user = new User(105, "kiran", "kochi");

		// When
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(user);

		// Then
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/save")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString);

		ResultActions resultActions = mockMvc.perform(reqBuilder);

		assertEquals(201, resultActions.andReturn().getResponse().getStatus());
	}

	@Test
	@DisplayName("Test Find All Users")
	public void testFindAllUsers() throws Exception {
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/getUsers");

		String contentType = mockMvc.perform(reqBuilder).andReturn().getResponse().getContentType();

		assertEquals("application/json", contentType);
	}

}
