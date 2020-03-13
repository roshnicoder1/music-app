package com.ibm.controller;

//import static org.junit.Assert.*;
//
//import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.model.Recommendation;
import com.ibm.services.RecommendationService;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommandationController.class)
public class RecommandationControllerTest {
	
	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient RecommendationService service;
	@InjectMocks
	private RecommandationController recommendationController;
	private transient Recommendation recommendation;
	static List<Recommendation> listRecom;
	
	
	String var =" {\n" + 
			"            \"artists\": [\n" + 
			"              {\n" + 
			"                \"external_urls\": {\n" + 
			"                  \"spotify\": \"htts://open.spotify.com/artist/53A0W3U0s8diEn9RhXQhVz\"\n" + 
			"                },\n" + 
			"                \"href\": \"https://ap.spotify.com/v1/artists/53A0W3U0s8diEn9RhXQhVz\",\n" + 
			"                \"id\": \"53A0W3U0s8diEn9hXV\",\n" + 
			"                \"name\": \"Keane\",\n" + 
			"                \"type\": \"artist\",\n" + 
			"                \"uri\": \"spotify:artist:53A0En9RhXQhVz\"\n" + 
			"              }\n" + 
			"            ],\n" + 
			"            \"disc_number\": 1,\n" + 
			"            \"duration_ms\": 215880,\n" + 
			"            \"explicit\": false,\n" + 
			"            \"external_urls\": {\n" + 
			"              \"spotify\": \"https://open.spotify.com/track/0yac0FPhLRH9i9lOng3f81\"\n" + 
			"            },\n" + 
			"            \"href\": \"https://api.spotify.com/v1/tracks/0yac0FPhLRH9i9lOng3f81\",\n" + 
			"            \"id\": \"0yac0FPhLRH9i9lOng3f81\",\n" + 
			"            \"is_local\": false,\n" + 
			"            \"is_playable\": true,\n" + 
			"            \"linked_from\": {\n" + 
			"              \"external_urls\": {\n" + 
			"                \"spotify\": \"https://open.spotify.com/track/4r9PmSmbAOOWqaGWLf6M9Q\"\n" + 
			"              },\n" + 
			"              \"href\": \"https://api.spotify.com/v1/tracks/4r9PmSmbAOOWqaGWLf6M9Q\",\n" + 
			"              \"id\": \"4r9PmSmbAOOWqaGWLf6M9Q\",\n" + 
			"              \"type\": \"track\",\n" + 
			"              \"uri\": \"spotify:track:4r9PmSmbAOOWqaGWLf6M9Q\"\n" + 
			"            },\n" + 
			"            \"name\": \"Everybody's Changing\",\n" + 
			"            \"preview_url\": null,\n" + 
			"            \"track_number\": 4,\n" + 
			"            \"type\": \"track\",\n" + 
			"            \"uri\": \"spotify:track:0yac0FPhLRH9i9lOng3f81\"\n" + 
			"          }";

	//JSONObject data = new JSONObject( );
	Object obj = var;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(recommendationController).build();
		recommendation = new Recommendation(new ObjectId("5e68b690226fc421d1395699"),"hi@gmail.com","53A0W3U0s8diEn9hXV",null);
		
	}
	
	@Test
	public void testInsertRecommendationFailure() throws Exception{
		//System.out.println("recommendation");
		Mockito.when(service.addRecommendation(recommendation)).thenReturn(recommendation);
        mvc.perform(MockMvcRequestBuilders.post("/musix/recommandation/add").contentType(MediaType.APPLICATION_JSON).content(jsonToString(recommendation)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        
//        Mockito.when(userService.saveOrUpdate(user)).thenReturn(user);
//        mockMvc.perform(MockMvcRequestBuilders.post("/musix/user").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testdeleteRecommendationByIdFailure() throws Exception{
		when(service.deleteRecommendation(recommendation)).thenReturn(true);
		mvc.perform(delete("/musix/delete/recommandation/53A0W3U0s8diEn9hXV/hi@gmail.com",1)).andExpect(status().isBadRequest());
//		verify(service,times(1)).deleteRecommendation("1235","102");
//		verifyNoMoreInteractions(service);
	}
	
	private static String jsonToString(final Object obj) throws  JsonProcessingException{
		String result;
		try{
			final ObjectMapper mapper=new ObjectMapper();
			final String jsonContent=mapper.writeValueAsString(obj);	
			result=jsonContent;
		}catch(JsonProcessingException e){
			result="Json Processing Error";
		}
		return result;
	}

}
