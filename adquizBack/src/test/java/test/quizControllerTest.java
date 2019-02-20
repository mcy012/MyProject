package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import adquiz.Application;
import adquiz.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional



public class quizControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private adquiz.Controller Controller;

    @Before
    public void setUp() {

        mockMvc = standaloneSetup(Controller).build();
    }

    @Test
    public void testGoodsList() throws Exception {

        mockMvc.perform(get("/goodsList/1")

        )
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void testBuy() throws Exception {

    }
}