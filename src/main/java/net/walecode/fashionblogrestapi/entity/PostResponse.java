package net.walecode.fashionblogrestapi.entity;

import lombok.*;
import net.walecode.fashionblogrestapi.dto.PostDTO;

import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private List<PostDTO> content;

    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean last;
}
