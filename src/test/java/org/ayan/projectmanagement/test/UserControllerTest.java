package org.ayan.projectmanagement.test;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import org.ayan.projectmanagement.dto.User;
import org.ayan.projectmanagement.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	private UserService userService;

	@Autowired
	ObjectMapper mapper;

	@Test
	public void getAllUsersTest() throws Exception {
		List<User> users = new ArrayList<User>();

		given(this.userService.getAllUsers()).willReturn(users);

		String uri = "/users";

		this.mvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void getUserTest() throws Exception {

		Integer id = 3;
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setUserid(id);
		users.add(user);

		given(this.userService.getUser(id)).willReturn(users);

		StringBuilder uri = new StringBuilder();
		uri.append("/user/");
		uri.append(id.toString());

		this.mvc.perform(MockMvcRequestBuilders.get(uri.toString())).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void addUserTest() throws Exception {
		User user = new User();
		user.setUserid(1);
		String uri = "/user";
		
		
		this.mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void updateUserTest() throws Exception {
		User user = new User();
		user.setUserid(10);
		user.setFirstname("Ayan");
		user.setEmpid(10);
		user.setEmpid(10);
		user.setProjectid(10);
		user.setTaskid(10);
		String uri = "/user";
		Integer rc = 1;
		
		given(this.userService.updateUser(user)).willReturn(rc.intValue());
		
		//mapper.writeValueAsString(user);
		
		this.mvc.perform(MockMvcRequestBuilders.patch(uri).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(user))).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
	@Test
	public void deleteUserTest() throws Exception {
		Integer id = 1;
		User user = new User();
		user.setUserid(id);
		user.setFirstname("Ayan");
		StringBuilder uri = new StringBuilder();
		uri.append("/user/");
		uri.append(id.toString());
		
		given(this.userService.deleteUser(user.getUserid())).willReturn(1);
		
		this.mvc.perform(MockMvcRequestBuilders.delete(uri.toString())).andExpect(MockMvcResultMatchers.status().isOk());

	}
	
}
