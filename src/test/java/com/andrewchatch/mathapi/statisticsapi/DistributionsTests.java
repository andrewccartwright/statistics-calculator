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
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Binomial;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Exponential;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Geometric;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Normal;
import com.andrewchatch.mathapi.statisticsapi.methods.distributions.Poisson;

@SpringBootTest
@AutoConfigureMockMvc
public class DistributionsTests {
    
    @Autowired
    private MockMvc mockMvc;

    private Binomial binomialBody = new Binomial(3,5,0.4);
    private Exponential exponentialBody = new Exponential(0.4, 4);
    private Normal normalBody = new Normal(10,15,6);
    private Geometric geometricBody = new Geometric(7, 0.5, true);
    private Poisson poissonBody = new Poisson(4, 6);

    @Test
    public void binomialTest() throws Exception {
        this.mockMvc.perform(post("/distributions/binomial")
            .content(Helper.asJsonString(binomialBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['P(X = x)']").value(0.23040000000000005))
        .andExpect(jsonPath("$['P(X \u2265 x)']").value(0.9129600000000001))
        .andExpect(jsonPath("$['P(X > x)']").value(0.0870399999999999))
        .andExpect(jsonPath("$['P(X \u2264 x)']").value(0.31743999999999994))
        .andExpect(jsonPath("$['P(X < x)']").value(0.6825600000000001));
    }

    @Test
    public void exponentialTest() throws Exception {
        this.mockMvc.perform(post("/distributions/exponential")
            .content(Helper.asJsonString(exponentialBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['P(X > x)']").value(0.20189651799465547))
        .andExpect(jsonPath("$['P(X < x)']").value(0.7981034820053445));
    }

    @Test
    public void normalTest() throws Exception {
        this.mockMvc.perform(post("/distributions/normal")
            .content(Helper.asJsonString(normalBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['Z Score']").value(-0.8333333333333334))
        .andExpect(jsonPath("$['P(X < x)']").value(0.20039484705483135))
        .andExpect(jsonPath("$['P(X > x)']").value(0.7996051529451687));
    }

    @Test
    public void geometricTest() throws Exception {
        this.mockMvc.perform(post("/distributions/geometric")
            .content(Helper.asJsonString(geometricBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['P(X = x)']").value(0.0078125))
        .andExpect(jsonPath("$['P(X \u2265 x)']").value(0.0625))
        .andExpect(jsonPath("$['P(X > x)']").value(0.9375))
        .andExpect(jsonPath("$['P(X \u2264 x)']").value(0.9453125))
        .andExpect(jsonPath("$['P(X < x)']").value(0.0546875));
    }

    @Test
    public void poissonTest() throws Exception {
        this.mockMvc.perform(post("/distributions/poisson")
            .content(Helper.asJsonString(poissonBody))
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['P(X = x)']").value(0.1338526175399834))
        .andExpect(jsonPath("$['P(X \u2265 x)']").value(0.2850565003166313))
        .andExpect(jsonPath("$['P(X > x)']").value(0.7149434996833687))
        .andExpect(jsonPath("$['P(X \u2264 x)']").value(0.848796117223352))
        .andExpect(jsonPath("$['P(X < x)']").value(0.15120388277664792));
    }

}
