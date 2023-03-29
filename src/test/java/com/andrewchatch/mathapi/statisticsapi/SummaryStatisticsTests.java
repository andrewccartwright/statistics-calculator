package com.andrewchatch.mathapi.statisticsapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.andrewchatch.mathapi.Helper;
import com.andrewchatch.mathapi.statisticsapi.methods.summary_statistics.SummaryStatistics;

@SpringBootTest
@AutoConfigureMockMvc
public class SummaryStatisticsTests {
    @Autowired
    private MockMvc mockMvc;

    private double[] array = {1.0,2.0,3,3.6,9,12.4};
    private SummaryStatistics body = new SummaryStatistics(array);

    @Test
    public void summaryStatsTest() throws Exception {
        this.mockMvc.perform(post("/summary_statistics")
            .content(Helper.asJsonString(body))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['Mean']").value(5.166666666666667))
        .andExpect(jsonPath("$['Maximum']").value(12.4))
        .andExpect(jsonPath("$['Minimum']").value(1.0))
        .andExpect(jsonPath("$['Range']").value(11.4))
        .andExpect(jsonPath("$['Count']").value(6.0))
        .andExpect(jsonPath("$['Sum']").value(31.0))
        .andExpect(jsonPath("$['Median']").value(3.3))
        .andExpect(jsonPath("$['Mode']").value(1.0))
        .andExpect(jsonPath("$['Variance']").value(20.31066666666667))
        .andExpect(jsonPath("$['Standard Deviation']").value(4.506735699668516));
    }
}
