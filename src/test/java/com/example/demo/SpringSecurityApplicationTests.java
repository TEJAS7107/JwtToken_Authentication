package com.example.demo;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.config.JwtService;


@SpringBootTest
@AutoConfigureMockMvc
class SpringSecurityApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	JwtService service;
	
    @Autowired
    private MockMvc mockMvc;
	
    @Test
    public void shouldReturnDemoController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/DempController")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
               
    }
    
    @Test
    public void shouldReturnAuthentication() throws Exception {
    	 String requestBody = "{\"email\": \"tejaskadam@gmail.com\", \"password\": \"Tejas@29\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
        		.content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
               
    }
    
    @Test
    public void shouldReturnRegister() throws Exception {
   	 String requestBody = "{\"firstname\": \"Dinesh\", \"lastname\": \"Pamu\", \"email\": \"dineshpamu1710@gmail.com\", \"password\": \"dinesh@Shreyashi\"}";
       mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
       		.content(requestBody)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk());
              
   }



}
