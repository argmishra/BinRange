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
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/binRangeInfoCache")
                .content(objectMapper.writeValueAsString(binRangeInfo))
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void updateBinRangeInfoByRef_success() throws Exception {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/binRangeInfoCache/{ref}", binRangeInfo.getRef())
            .content(objectMapper.writeValueAsString(binRangeInfo))
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deleteBinRangeInfoByRef_success() throws Exception {
        BinRangeInfo binRangeInfo = BinRangeInfoTest.generateTestEntity();

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/binRangeInfoCache/{ref}", binRangeInfo.getRef())
            .content(objectMapper.writeValueAsString(binRangeInfo))
                .contentType(MediaType.APPLICATION_JSON);

        builder = MockMvcRequestBuilders
            .delete("/binRangeInfoCache/{ref}", binRangeInfo.getRef());
        MockHttpServletResponse response = getMockMvc().perform(builder).andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

}