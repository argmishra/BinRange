package com.mastercard.evaluation.bin.range.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.evaluation.bin.range.models.BinRangeInfo;
import com.mastercard.evaluation.bin.range.models.BinRangeInfoTest;
import com.mastercard.evaluation.bin.range.services.BinRangeService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BinRangeInfoCacheControllerTest extends BaseControllerTest {

    private BinRangeService binRangeService = mock(BinRangeService.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BinRangeInfoCacheController binRangeInfoCacheController;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(binRangeInfoCacheController, "binRangeService", binRangeService);
    }

    @Test
    public void createBinRangeInfo_success() throws Exception {
        BinRangeInfo requestBody = BinRangeInfoTest.generateTestEntity();
        requestBody.setRef(null);
        BinRangeInfo returnType = BinRangeInfoTest.generateTestEntity();
        when(binRangeService.createBinRangeInfo(requestBody)).thenReturn(returnType);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/binRangeInfoCache")
            .content(objectMapper.writeValueAsString(requestBody)).contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();

        BinRangeInfo responseBody = objectMapper.readValue(response.getContentAsString(), BinRangeInfo.class);

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertNotNull(responseBody.getRef());
        assertEquals("EURO",responseBody.getCurrencyCode());
        assertEquals("KBC",responseBody.getBankName());
    }

    @Test
    public void updateBinRangeInfo_success() throws Exception {
        BinRangeInfo requestBody = BinRangeInfoTest.generateTestEntity();
        requestBody.setRef(null);
        BinRangeInfo returnType = BinRangeInfoTest.generateTestEntity();
        when(binRangeService.createBinRangeInfo(requestBody)).thenReturn(returnType);
        when(binRangeService.getBinRangeInfo(returnType.getRef())).thenReturn(returnType);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/binRangeInfoCache")
            .content(objectMapper.writeValueAsString(requestBody)).contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();

        BinRangeInfo responseBody = objectMapper.readValue(response.getContentAsString(), BinRangeInfo.class);

        requestBody = BinRangeInfoTest.generateTestEntity();
        requestBody.setBankName("AIB");

        builder = MockMvcRequestBuilders
            .put("/binRangeInfoCache/{ref}", responseBody.getRef())
            .content(objectMapper.writeValueAsString(requestBody)).contentType(MediaType.APPLICATION_JSON);
        response = getMockMvc().perform(builder).andReturn().getResponse();

        responseBody = objectMapper.readValue(response.getContentAsString(), BinRangeInfo.class);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("AIB",responseBody.getBankName());
    }

    @Test
    public void deleteBinRangeInfo_success() throws Exception {
        BinRangeInfo requestBody = BinRangeInfoTest.generateTestEntity();
        requestBody.setRef(null);
        BinRangeInfo returnType = BinRangeInfoTest.generateTestEntity();
        when(binRangeService.createBinRangeInfo(requestBody)).thenReturn(returnType);
        when(binRangeService.getBinRangeInfo(returnType.getRef())).thenReturn(returnType);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/binRangeInfoCache")
            .content(objectMapper.writeValueAsString(requestBody)).contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();

        BinRangeInfo responseBody = objectMapper.readValue(response.getContentAsString(), BinRangeInfo.class);

        builder = MockMvcRequestBuilders
            .delete("/binRangeInfoCache/{ref}", responseBody.getRef());
        response = getMockMvc().perform(builder).andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

}