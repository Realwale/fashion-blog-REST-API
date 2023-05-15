package net.walecode.fashionblogrestapi.service.impl;

import net.walecode.fashionblogrestapi.dto.CommentDTO;
import net.walecode.fashionblogrestapi.dto.LikeDTO;
import net.walecode.fashionblogrestapi.entity.Comment;
import net.walecode.fashionblogrestapi.entity.Like;
import net.walecode.fashionblogrestapi.entity.Post;
import net.walecode.fashionblogrestapi.exception.BlogAPIException;
import net.walecode.fashionblogrestapi.exception.ResourceNotFoundException;
import net.walecode.fashionblogrestapi.repository.CommentRepository;
import net.walecode.fashionblogrestapi.repository.LikeRepository;
import net.walecode.fashionblogrestapi.repository.PostRepository;
import net.walecode.fashionblogrestapi.service.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LikeServiceImpl implements LikeService {

    private LikeRepository likeRepository;

    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    //Using ModelMapper library
    private LikeDTO mapToDTO (Like like){
        return modelMapper.map(like, LikeDTO.class);
    }

    private Like mapToLike (LikeDTO likeDTO){
        return modelMapper.map(likeDTO, Like.class);
    }

    @Override
    public LikeDTO createLike(long postId, LikeDTO likeDTO) {

        Like like = mapToLike(likeDTO);


        // Retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        //Set post to comment entity
        like.setPost(post);

        //Save like entity into DB
        Like newLike = likeRepository.save(like);

        return mapToDTO(newLike);
    }


    @Override
    public List<LikeDTO> getLikeByPostId(long postId) {
            List<Like> likes = likeRepository.findByPostId(postId);
            return likes.stream().map(this::mapToDTO).collect(Collectors.toList());
        }

    @Override
    public LikeDTO getLikeById(Long postId, Long likeId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        //retrieve like by id
        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", likeId));

        if(!like.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Like does not belong to post");
        }
        return mapToDTO(like);
    }

    @Override
    public LikeDTO updateLike(Long postId, Long likeId, LikeDTO likeRequest) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));


        Like like = likeRepository.findById(likeId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", likeId));

        if (!like.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Like does not belong to post");

        }

        like.setIsLiked(Boolean.valueOf(likeRequest.getIsLiked()));

        Like updatedLike = likeRepository.save(like);

        return mapToDTO(updatedLike);
    }
}
