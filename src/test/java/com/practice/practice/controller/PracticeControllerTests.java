package com.practice.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.practice.model.Practice;
import com.practice.practice.repository.PracticeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.test.web.servlet.MvcResult;



import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PracticeController.class)
public class PracticeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper=new ObjectMapper();

    @MockBean
    private PracticeRepository practiceRepository;

    @Test
    public void testGetAllPractices() throws Exception {
        List<Practice> listPractices = new ArrayList<>();
        listPractices.add(new Practice(1,
                "Yes Bank",
                "sample 1",
                LocalDateTime.of(2022, 07, 14, 12, 00, 00),
                LocalDateTime.of(2022, 07, 14, 12, 00, 00)));
        listPractices.add(new Practice(2,
                "SBI Bank",
                "sample 2",
                LocalDateTime.of(2022, 07, 14, 12, 00, 00),
                LocalDateTime.of(2022, 07, 14, 12, 00, 00)));
        listPractices.add(new Practice(3,
                "HDFC Bank",
                "sample 3",
                LocalDateTime.of(2022, 07, 14, 12, 00, 00),
                LocalDateTime.of(2022, 07, 14, 12, 00, 00)));
        Mockito.when(practiceRepository.findAll()).thenReturn(listPractices);

        String url = "/api/v1/practice/";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);
//        if(listPractices!=null)
        String expectedJsonResponse = objectMapper.writeValueAsString(listPractices);
//        System.out.println(objectMapper.writeValueAsString(listPractices));
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testGetPracticeById() throws Exception{
        Practice practice = new Practice(
                1,
                "Airtel",
                "Optical",
                LocalDateTime.of(2018, 9, 11, 22, 00, 00),
                LocalDateTime.of(2019, 9, 11, 22, 00, 00)
        );
        long practiceId = 1;
        String url = "/api/v1/practice/" + practiceId;
//        System.out.println(url);
//        if(practice!=null)
        Mockito.when(practiceRepository.findById(practiceId)).thenReturn(Optional.of(practice));
        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(practice);
//        System.out.println(expectedJsonResponse);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testCreatePractice() throws Exception{
        Practice newPractice=new Practice(
                1,
                "Yes Bank",
                "sample",
                LocalDateTime.of(2022,7,14,14,00,00),
                LocalDateTime.of(2022,8,14,14,00,00));

        Practice savedPractice= new Practice(
                1,
                "Yes Bank",
                "sample",
                LocalDateTime.of(2022,7,14,14,00,00),
                LocalDateTime.of(2022,8,14,14,00,00));

        Mockito.when(practiceRepository.save(newPractice)).thenReturn(savedPractice);

        String url="/api/v1/practice/create-downtime";
        MvcResult mvcResult=mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newPractice))
        )
                .andExpect(status().isOk())
                .andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();
        assertThat(actualJsonResponse).isNotNull();
    }

    @Test
    public void testUpdatePractice() throws Exception{
        Practice oldPractice= new Practice(1,"Airtel",
                "Optical",
                LocalDateTime.of(2018, 9, 11, 22, 00, 00),
                LocalDateTime.of(2019, 9, 11, 22, 00, 00)
        );
        long practiceId = 0;
        String url = "/api/v1/practice/update/" + practiceId;
        Practice newPractice = new Practice(1,"Airtel",
                "Classified",
                LocalDateTime.of(2018, 9, 11, 22, 00, 00),
                LocalDateTime.of(2019, 9, 11, 22, 00, 00)
        );
        Mockito.when(practiceRepository.findById(practiceId)).thenReturn(Optional.of(newPractice));
        Mockito.when(practiceRepository.save(newPractice)).thenReturn(newPractice);
        MvcResult mvcResult = mockMvc.perform(
                put(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newPractice))
        ).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(newPractice);
        assertThat(actualJsonResponse).isNotNull();
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testDeletePractice() throws Exception{
        Practice practice = new Practice(1,"Airtel",
                "Optical",
                LocalDateTime.of(2018, 9, 11, 22, 00, 00),
                LocalDateTime.of(2019, 9, 11, 22, 00, 00)
        );
        long practiceId = 1;
        String url = "/api/v1/practice/delete/" + practiceId;
        Mockito.when(practiceRepository.findById(practiceId)).thenReturn(Optional.of(practice));
        Mockito.doNothing().when(practiceRepository).delete(practice);
        MvcResult mvcResult = mockMvc.perform(delete(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        Map<String, Boolean> expectedResponse = new HashMap<>();
        expectedResponse.put("deleted", Boolean.TRUE);
        String expectedJsonResponse = objectMapper.writeValueAsString(expectedResponse);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

}
