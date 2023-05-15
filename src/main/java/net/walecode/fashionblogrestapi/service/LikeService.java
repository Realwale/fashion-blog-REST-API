package net.walecode.fashionblogrestapi.service;


import net.walecode.fashionblogrestapi.dto.LikeDTO;

import java.util.List;

public interface LikeService {

    LikeDTO createLike(long postId, LikeDTO likeDTO);

    List<LikeDTO> getLikeByPostId(long postId);

    LikeDTO getLikeById(Long postId, Long likeId);

    LikeDTO updateLike(Long postId, Long likeId, LikeDTO likeRequest);

}