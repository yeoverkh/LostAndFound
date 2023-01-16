package lostandfound;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void redirectionTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main"));
    }

    @Test
    public void mainPageTest() throws Exception {
        mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(content().string(containsString("Lost items")))
                .andExpect(content().string(containsString("Sort by")))
                .andExpect(content().string(containsString("Search")));
    }

    @Test
    public void emptyItemsTest() throws Exception {
        mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(xpath("/html/body/div/div[2]/h6").string("No items yet"));
    }
}
