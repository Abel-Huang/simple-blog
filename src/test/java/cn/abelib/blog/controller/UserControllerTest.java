package cn.abelib.blog.controller;

import cn.abelib.blog.pojo.User;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;




/**
 * Created by abel on 2018/1/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserController())
                .build();
    }

    @Test
    public void listTest()throws Exception{
        RequestBuilder request = null;
        request = MockMvcRequestBuilders.get("/users");
//        mockMvc.perform(request)
//                .andExpect(req)
    }

    @Test
    public void listAndPageTest()throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.
                get("127.0.0.1:8080/users/query")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
         String str = result.getResponse().getContentAsString();
         System.err.println(str);
    }
    @Test
    public void addUser() throws Exception{
//        User user = new User("huangjjj", "123@126.com", "hjjj", "122333", "/data/avator/huangjinjin.jpg");
//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(user, User.class);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//                .post("/users/add")
//                .accept(MediaType.APPLICATION_JSON)
//                .content(jsonStr))
//                .andReturn();
//
//        int status = result.getResponse().getStatus();
//        String str = result.getResponse().getContentAsString();
//        System.err.println(str);
//        System.err.println(status);
    }


}
