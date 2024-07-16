package com.retail.controller;

import com.retail.PayloadHelper;
import com.retail.TestUtils;
import com.retail.service.StoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class StoreControllerTest {

    private static final String STORE_URL = "/store/bill";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StoreService service;
    @Test
    public void testBill_SUCCESS() throws Exception {
        mockMvc.perform(
                        post(STORE_URL)
                                .content(TestUtils.mapToJson(PayloadHelper.getCustomer()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());


    }

}
