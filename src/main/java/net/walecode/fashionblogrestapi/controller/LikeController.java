package net.walecode.fashionblogrestapi.controller;

import jakarta.validation.Valid;
import net.walecode.fashionblogrestapi.dto.LikeDTO;
import net.walecode.fashionblogrestapi.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<LikeDTO> createLike(@PathVariable(value = "postId") long postId,
                                              @Valid @RequestBody LikeDTO likeDTO){

        return new ResponseEntity<>(likeService.createLike(postId, likeDTO),
                HttpStatus.CREATED);
    }

}
