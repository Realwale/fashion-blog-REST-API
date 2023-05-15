package net.walecode.fashionblogrestapi.service;

import net.walecode.fashionblogrestapi.dto.PostDTO;
import net.walecode.fashionblogrestapi.entity.PostResponse;
import org.modelmapper.ModelMapper;

import java.util.List;

public interface PostService {


    PostDTO createPost(PostDTO postDTO);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);

    void deletePostById(long id);

    List<PostDTO> getPostsByCategory(Long categoryId);

}
