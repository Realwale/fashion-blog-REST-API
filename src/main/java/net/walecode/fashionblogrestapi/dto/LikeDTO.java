package net.walecode.fashionblogrestapi.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LikeDTO {

    private Long id;

    private String isLiked;
}
