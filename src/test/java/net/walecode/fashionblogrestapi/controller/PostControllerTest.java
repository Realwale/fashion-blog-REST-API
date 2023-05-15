//package net.walecode.fashionblogrestapi.controller;
//
//import net.walecode.fashionblogrestapi.dto.PostDTO;
//import net.walecode.fashionblogrestapi.entity.PostResponse;
//import net.walecode.fashionblogrestapi.service.PostService;
//import net.walecode.fashionblogrestapi.utils.AppConstants;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//
//public class PostControllerTest {
//
//    @Mock
//    private PostService postService;
//
//    @InjectMocks
//    private PostController postController;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    @DisplayName("Create Post Test")
//    public void createPostTest() throws Exception {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setTitle("Post Title");
//        postDTO.setContent("Post Content");
//
//        when(postService.createPost(postDTO)).thenReturn(postDTO);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/posts")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{ \"title\": \"Post Title\", \"content\": \"Post Content\" }")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Post Title"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Post Content"));
//    }
//
//    @Test
//    @DisplayName("Get All Posts Test")
//    public void getAllPostsTest() throws Exception {
//        when(postService.getAllPosts(0, Integer.parseInt(AppConstants.DEFAULT_PAGE_SIZE), AppConstants.DEFAULT_SORT_BY, AppConstants.DEFAULT_SORT_DIRECTION))
//                .thenReturn(new PostResponse());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}
