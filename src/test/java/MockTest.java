import com.company.project.Application;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class MockTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();  //构造MockMvc
    }

    @Test
    public void test() throws Exception {
        mvc.perform(post("/user/detail") // //调用接口
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("id", "1")//传入添加的用户参数 

                .accept(MediaType.APPLICATION_JSON))  //接收的类型
                .andExpect(status().isOk())   //判断接收到的状态是否是200
                .andDo(print())  //打印内容
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(Matchers.containsString("200")))
        ; //匹配返回值中的内容

    }





}
