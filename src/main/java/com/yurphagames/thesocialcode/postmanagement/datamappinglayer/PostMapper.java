package com.yurphagames.thesocialcode.postmanagement.datamappinglayer;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.PostData;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.models.PostRequestModel;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.models.PostResponseModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE;

    public PostResponseModel entityToResponseModel(Post post){
        if(post == null) return null;

        PostResponseModel responseModel = new PostResponseModel();

        responseModel.setUserHandle(
                post.getUserHandle() == null
                ? null : String.valueOf(post.getUserHandle())
        );

        if(post.getPostData().getPostName() != null){
            responseModel.setPostName(post.getPostData().getPostName());
        }

        responseModel.setLikeCount(post.getPostData().getLikeCount());
        responseModel.setViewCount(post.getPostData().getViewCount());
        if(post.getPostDate() != null){
            responseModel.setPostDate(post.getPostDate().format(DATE_FORMAT));
        }

        responseModel.setSearchTitle(post.getSearchTitle());

        return responseModel;

    }


    public List<PostResponseModel> entityToResponseModelList(List<Post> posts){
        if(posts == null) return List.of();

        return posts.stream().map(this::entityToResponseModel).collect(Collectors.toList());
    }

    public Post requestModelToEntity(PostRequestModel requestModel){
        if(requestModel == null) return null;

        Post post = new Post();

        post.setUserHandle(requestModel.getUserHandle());
        post.setPostData(new PostData(requestModel.getLikeCount(), requestModel.getViewCount(), requestModel.getPostName()));

        return post;
    }

    public void updateEntityFromRequestModel(
            @NotNull PostRequestModel requestModel,
            @NotNull Post post
    ){
        post.setUserHandle(requestModel.getUserHandle());
        post.setPostData(new PostData(requestModel.getLikeCount(), requestModel.getViewCount(), requestModel.getPostName()));
    }

}
