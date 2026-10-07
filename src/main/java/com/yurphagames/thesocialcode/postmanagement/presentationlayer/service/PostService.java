package com.yurphagames.thesocialcode.postmanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.infrustructurelayer.repository.PostRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService implements PostServiceInterface{
    private final PostRepository postRepository;
    private final String STORAGE_PATH = "uploads";
    private int searchCountAmount = 4;
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }


    public ResponseEntity<InputStreamResource> getById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Post not found"
                        ));

        String path = post.getPath();

        if(path == null || path.trim().isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Post path is empty"
            );

        try{
            Path root = Paths.get(STORAGE_PATH).toAbsolutePath().normalize();
            Path postPath = root.resolve(path).normalize();

            if(!postPath.startsWith(root))
                throw new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "Invalid path"
                );

            if(!Files.exists(postPath) || !Files.isRegularFile(postPath))
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "File not found"
                );

            InputStream input = Files.newInputStream(postPath);
            String postName = getFileName(path);
            String contentType = getContentType(postName);
            InputStreamResource resource = new InputStreamResource(input);

            ResponseEntity.BodyBuilder builder =
                    ResponseEntity.ok()
                            .contentType(
                                    MediaType.parseMediaType(contentType)
                            )
                            .header(
                                    "Content-Disposition",
                                    "inline; filename=\""
                                    + postName + "\""
                            )
                            .header(
                                    "Accept-Ranges",
                                    "bytes"
                            );

            builder.contentLength(Files.size(postPath));

            return builder.body(resource);


        } catch (IOException e) {
            e.printStackTrace();

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Could not read file"
            );
        }
    }



    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostByParams(String username, String postname, int index) {
        username = username.trim();
        postname = postname.trim();

        if(username.isEmpty()){

            // This is a check to see if both the username & postname is empty
            // so there is no indexable way to find any posts
            // so we will just return the posts at that index

            if(postname.isEmpty())
                return convertPostsIntoSegments(getAll()).get(index);


            // This is now returning all the posts that
            // have the postname given in the parameters

            String finalPostname = postname;
            return convertPostsIntoSegments(getAll()).get(index)
                    .stream().filter(post ->
                            getFileName(post.getPath())
                                    .equals(finalPostname)
                    ).toList();

        }

        // Now if we made it here that means that a username
        // has been given therefore we can use that as
        // a search parameter

        List<Post> postsByusername =
                findByUsername(username, index);

        // I need to check also if the postname is
        // empty also since my process can have
        // username = empty, postname = empty
        // username = not empty, postname = empty
        // username = empty, postname = not empty,
        // username = not empty, postname = not empty

        // So with that said I need to return right away
        // since there is no postname added in.
        // The returned results will only be filtered by
        // the username

        if(postname.isEmpty())
            return postsByusername;


        // Now if we made it here that means
        // that both the username and postname
        // are not empty so what we got to do now
        // is filter out the posts from the user with
        // that username which does not use that
        // postname

        String finalPostname1 = postname;
        return postsByusername.stream()
                .filter(post ->
                        getFileName(post.getPath())
                                .equals(finalPostname1)
                        ).toList();

    }

    @Override
    public Post findByPath(String path) {
        return null;
    }

    @Override
    public List<Post> findByUsername(String username, int index) {
        // first we need to get all the posts
        List<Post> posts = getAll();
        // Then we need to filter the posts to only
        // be with the paramenter username

        List<Post> filteredposts = posts.stream().filter(post ->
                post.getUsername().equals(username)
        ).toList();

        return convertPostsIntoSegments(filteredposts).get(index);
    }

    @Override
    public Post findByUsernameAndPostNumber(String username, int postnumber) {
        return null;
    }


    private HashMap<Integer,List<Post>> convertPostsIntoSegments(List<Post> all){
        HashMap<Integer, List<Post>> segmented = new HashMap<Integer, List<Post>>();
        while(true){

            ArrayList<Post> posts = new ArrayList<>();
            for(int i = 0; i < searchCountAmount - 1; i++){
                posts.add(all.get(i));
                if(i > all.size() - 1)
                    break;
            }
            segmented.put(segmented.size() + 1, new ArrayList<Post>());
            all.removeAll(posts);

            if(all.isEmpty())
                break;
        }

        return segmented;
    }

    private String getFileName(String path){
        if(path.isEmpty() || path == null)
            return  "";

        return path.replace("\\", "/").substring(path.lastIndexOf("/") + 1);
    }

    private String getContentType(String postName) {

        postName = postName.toLowerCase();

        if (postName.endsWith(".png") ||
                postName.endsWith(".jpg") ||
                postName.endsWith(".jpeg") ||
                postName.endsWith(".gif") ||
                postName.endsWith(".webp")) {

            return "image/" + getExtension(postName);
        }

        if (postName.endsWith(".mp4") ||
                postName.endsWith(".webm") ||
                postName.endsWith(".mov") ||
                postName.endsWith(".mkv")) {

            if (postName.endsWith(".mp4"))
                return "video/mp4";

            if (postName.endsWith(".webm"))
                return "video/webm";

            if (postName.endsWith(".mov"))
                return "video/quicktime";

            return "video/x-matroska";
        }

        if (postName.endsWith(".mp3") ||
                postName.endsWith(".wav")) {

            if (postName.endsWith(".mp3"))
                return "audio/mpeg";

            return "audio/wav";
        }

        if (postName.endsWith(".txt") ||
                postName.endsWith(".csv") ||
                postName.endsWith(".json") ||
                postName.endsWith(".xml") ||
                postName.endsWith(".yml") ||
                postName.endsWith(".java") ||
                postName.endsWith(".kts") ||
                postName.endsWith(".cpp") ||
                postName.endsWith(".cs") ||
                postName.endsWith(".py") ||
                postName.endsWith(".js") ||
                postName.endsWith(".jsx") ||
                postName.endsWith(".ts") ||
                postName.endsWith(".tsx") ||
                postName.endsWith(".html") ||
                postName.endsWith(".css") ||
                postName.endsWith(".sql")) {

            return "text/plain; charset=UTF-8";
        }

        return MediaType.APPLICATION_OCTET_STREAM_VALUE;
    }

    private String getExtension(String fileName) {

        int dot = fileName.lastIndexOf(".");

        return fileName.substring(dot + 1);
    }
}

