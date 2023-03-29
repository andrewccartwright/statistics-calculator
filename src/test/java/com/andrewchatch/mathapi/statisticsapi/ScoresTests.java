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
import com.andrewchatch.mathapi.statisticsapi.methods.scores.TScore;
import com.andrewchatch.mathapi.statisticsapi.methods.scores.ZScore;

@SpringBootTest
@AutoConfigureMockMvc
public class ScoresTests {
    
    @Autowired
    private MockMvc mockMvc;

    private TScore tScoreBody = new TScore(6,10,30,100);
    private ZScore zScoreBody = new ZScore(4, 5, 0.7);

    @Test
    public void tScoreTest() throws Exception {
        this.mockMvc.perform(post("/scores/t_score")
            .content(Helper.asJsonString(tScoreBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['T Score']").value(-1.3333333333333333))
        .andExpect(jsonPath("$['P(T < t)']").value(0.09274117500105655))
        .andExpect(jsonPath("$['P(T > t)']").value(0.9072588249989435));
    }

    @Test
    public void zScoreTest() throws Exception {
        this.mockMvc.perform(post("/scores/z_score")
            .content(Helper.asJsonString(zScoreBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['Z Score']").value(-1.4285714285714286))
        .andExpect(jsonPath("$['P(X < x)']").value(0.07095128493808589))
        .andExpect(jsonPath("$['P(X > x)']").value(0.9290487150619141));
    }
}
