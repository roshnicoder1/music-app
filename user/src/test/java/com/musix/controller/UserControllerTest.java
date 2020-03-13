package com.musix.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musix.controller.UserController;
//import com.slm.exceptions.UserAlreadyExistsException;
//import com.slm.model.Match;
import com.musix.model.User;
import com.musix.service.LogService;
import com.musix.service.UserService;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

//import org.bson.types.ObjectId;
//import com.slm.service.UserAuthenticationService;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mockito;
 import org.mockito.MockitoAnnotations;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.http.MediaType;
 import org.springframework.test.context.junit4.SpringRunner;
 import org.springframework.test.web.servlet.MockMvc;
 import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
 import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
 import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
 import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import java.util.Date;
 @RunWith(SpringRunner.class)
 @WebMvcTest
public class UserControllerTest {
	 
	 @Autowired
     private MockMvc mockMvc;
     @MockBean
     private UserService userService;
     @MockBean
     private LogService logService;
     private User user;
     @InjectMocks
     private UserController authenticationController;
     @Before
     public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
         mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
         user = new User("hi@gmail.com","hitesh","shende","12345678","male","src",new Date());
         //         user.setUserId(1);
//         user.setUserName("Jhon Simon");
//         user.setUserEmail("jhonsimon@gmail.com");
//         user.setUserPassword("123456");
     }
      @Test
      public void testRegisterUser() throws Exception {
    	 Mockito.when(userService.saveOrUpdate(user)).thenReturn(user);
          mockMvc.perform(MockMvcRequestBuilders.post("/musix/user").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                  .andExpect(MockMvcResultMatchers.status().isOk());
//                .andDo(MockMvcResultHandlers.print());
      }
      
      @Test
      public void testLoginUser() throws Exception {


          String userId = "hi@gmail.com";
          String password = "12345678";

          System.out.println(user);
          Mockito.when(userService.saveOrUpdate(user)).thenReturn(user);
          Mockito.when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
          mockMvc.perform(MockMvcRequestBuilders.post("/musix/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
                  .andExpect(MockMvcResultMatchers.status().isOk());
      }

//      @Test
//      public void testRegisterUserFailure() throws Exception {
//    	 Mockito.when(userService.saveOrUpdate(any())).thenReturn(user);
//          mockMvc.perform(MockMvcRequestBuilders.post("/musix/user").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                  .andExpect(MockMvcResultMatchers.status().isOk());
////                .andDo(MockMvcResultHandlers.print());
//      }
      
//      @Test
//      public void testAuthenticate() throws Exception {
//    	  String token="eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIyIiwiaWF0IjoxNTUzMDc3NzQ0fQ.ob4OEBB1fBEv3aZdiv-mGzTdkVMEngfiHThhd2u8mdRLFpqGLzUPOwcaBVrGivMi";
//         when(userService.authenticate(user)).thenReturn(true);
//         when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
//          mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                  .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//      }
//     // Parsing String format data into JSON format
     private static String jsonToString(final Object obj) throws JsonProcessingException {
         String result;
         try {
             final ObjectMapper mapper = new ObjectMapper();
             final String jsonContent = mapper.writeValueAsString(obj);
             result = jsonContent;
         } catch (JsonProcessingException e) {
             result = "Json processing error";
         }
         return result;
     }

}
