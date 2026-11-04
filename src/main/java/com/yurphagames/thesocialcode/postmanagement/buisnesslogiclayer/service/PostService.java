package com.yurphagames.thesocialcode.postmanagement.buisnesslogiclayer.service;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.PostDirectories;
import com.yurphagames.thesocialcode.postmanagement.infrustructurelayer.repository.PostRepository;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.SubscriptionType;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.businesslogiclayer.service.UserService;


import jakarta.persistence.EntityNotFoundException;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService implements PostServiceInterface{
    private final PostRepository postRepository;
    private final UserService userService;
    private final String STORAGE_PATH = "uploads";
    private int searchCountAmount = 4;
    public PostService(PostRepository postRepository, UserService userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<InputStreamResource> getBysearchTitle(String searchTitle){
        Post post = postRepository.findBySearchTitle(searchTitle);

        if(post == null) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Post not found"
        );

        String path = post.getPostDirectories().getPostPath();
        System.out.println(path);

        if(path == null || path.trim().isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Post path is empty"
            );

        try{
            Path root = Paths.get(STORAGE_PATH);
            Path postPath = Paths.get(path).normalize();


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
    public ResponseEntity<InputStreamResource> getById(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Post not found"
                        ));

        String path = post.getPostDirectories().getPostPath();
        System.out.println(path);

        if(path == null || path.trim().isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Post path is empty"
            );

        try{
            Path root = Paths.get(STORAGE_PATH);
            Path postPath = Paths.get(path).normalize();


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
    public List<Post> getPostByParams(String userhandle, String postname, int index) {
        userhandle = userhandle.trim();
        postname = postname.trim();

        // first I use userhandle instead of username since
        // userhandle is unique to a user and username
        // is not because my system has a id, a userHandle, and
        // a userName, a user has both id and userHandle as
        // unique values no other user can have that
        // but multiple users can have the same username

        if(userhandle.isEmpty()){

            // This is a check to see if both the userhandle & postname is empty
            // so there is no indexable way to find any posts
            // so we will just return the posts at that index

            if(postname.isEmpty())
                return convertPostsIntoSegments(getAll()).get(index);


            // This is now returning all the posts that
            // have the postname given in the parameters

            String finalPostname = postname;
            return convertPostsIntoSegments(getAll()).get(index)
                    .stream().filter(post ->
                            getFileName(post.getPostDirectories().getPostPath())
                                    .equals(finalPostname)
                    ).toList();

        }

        // Now if we made it here that means that a userhandle
        // has been given therefore we can use that as
        // a search parameter

        List<Post> postsByuserhandle =
                findByUserHandle(userhandle, index);

        // I need to check also if the postname is
        // empty also since my process can have
        // userhandle = empty, postname = empty
        // userhandle = not empty, postname = empty
        // userhandle = empty, postname = not empty,
        // userhandle = not empty, postname = not empty

        // So with that said I need to return right away
        // since there is no postname added in.
        // The returned results will only be filtered by
        // the userhandle

        if(postname.isEmpty())
            return postsByuserhandle;


        // Now if we made it here that means
        // that both the userhandle and postname
        // are not empty so what we got to do now
        // is filter out the posts from the user with
        // that userhandle which does not use that
        // postname

        System.out.println("\n\n\n\n\nMade it in to the last place\n\n\n\n\n\n" + postname +"\n" + postsByuserhandle.size() +"\n" + getFileName("uploads\\helldog\\post#1\\my_video_2.mp3\\my_video_2.mp3"));
        String finalPostname1 = postname;
        return postsByuserhandle.stream()
                .filter(post ->
                        getFileName(post.getPostDirectories().getPostPath())
                                .equals(finalPostname1)
                        ).toList();

    }



    @Override
    public List<Post> findByUserHandle(String userhandle, int index) {
        // first we need to get all the posts
        List<Post> posts = getAll();
        // Then we need to filter the posts to only
        // be with the paramenter userHandle

        List<Post> filteredposts = posts.stream().filter(post ->
                post.getUserHandle().equals(userhandle)
        ).toList();

        return convertPostsIntoSegments(filteredposts).get(index);
    }

    @Override
    public Post fingBySearchTitle(String searchTitle) {
        return postRepository.findBySearchTitle(searchTitle);
    }


    private HashMap<Integer,List<Post>> convertPostsIntoSegments(List<Post> all){
        HashMap<Integer, List<Post>> segmented = new HashMap<Integer, List<Post>>();
        all = new ArrayList<>(all);
        while(true){

            ArrayList<Post> posts = new ArrayList<>();
            for(int i = 0; i < searchCountAmount - 1; i++){

                if(i > all.size() - 1)
                    break;

                posts.add(all.get(i));
            }
            segmented.put(segmented.size() + 1, posts);
            all.removeAll(posts);

            if(all.isEmpty())
                break;
        }

        return segmented;
    }

    private String getFileName(String path){
        if(path == null || path.isEmpty())
            return  "";

        path = path.replace("\\", "/");
        return path.substring(path.lastIndexOf("/") + 1);
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

    @Override
    public void upload(MultipartFile file, String userhandle, String password, String description) throws IOException {

        if(userhandle.isEmpty() || userhandle == null)
            throw new IOException("userhandle cannot be empty");

        User user;

        try{
            user = userService.getUserByUserHandle(userhandle);
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User could not be found");
        }

        if(user.getSubscriptionType() == SubscriptionType.BASIC){

            double bytes = file.getSize();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            if(megabytes > 10){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You Do Not Have The Correct Subscription To Upload A File Bigger Than 10 mb");
            }
        }else{
            double bytes = file.getSize();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            if(megabytes > 40){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You Cannot Upload a file bigger than 40 mb on The Max Subscription");
            }
        }
        if(!user.getUserInfo().getPassword().equals(password))
            throw new IOException("Invalid password");


        if(file.isEmpty())
            throw new IOException("Empty File");

        // we need to add a postcount to the user so that
        // it can be represented later properly
        // We add +1 after since the original postcount is nto that
        // anymore but +1 of it
        int postNumber = user.getAccountData().getLiteralPostCount()+ 1;
        int userPostNumber = user.getAccountData().getPostCount() + 1;
        // We need to either create a folder in the
        // storage root for that user if he hasn't already
        // had one since we only create it once they post their
        // first post to reduce usage of storage.
        // And if it is created we do nothing but get the
        // reference to where it is located.

        Path folder = Paths.get(STORAGE_PATH).resolve(userhandle).normalize();
        if(!Files.exists(folder))
            Files.createDirectories(folder);

        // Now we need to check if the file is a video
        // because we need to do some compression
        // on it to allow the upload to be smaller than
        // what it is inputted as.
        String org_filename = file.getOriginalFilename();


        Path posterPath = folder.resolve("post#" + postNumber);
        if(!Files.exists(posterPath))
            Files.createDirectories(posterPath);
        // Now create a file for the interactions data like comments
        Path interactionFile = posterPath.resolve("interaction.txt");

        Files.createFile(interactionFile);

        String savedPath = posterPath.toString();

        Path finalPath = posterPath.resolve(org_filename);
        if(isVideoFile(org_filename)){

            // Get the extension of the video to
            // keep the compress result correct.
            String extension = getExtension(org_filename);

            // Now we need to create a temp file
            // this file will be used to edit the file
            // and we need a temp output
            // this will allow us to make it so
            // that if the compressed file
            // is actually larger than the
            // original file than we won't
            // replace the file to it.

            Path tempIn = Files.createTempFile("in_", "." + extension);
            Path tempOut = Files.createTempFile("out_", "." + extension);
            try{
                try(InputStream inputStream = file.getInputStream()){
                    Files.copy(inputStream, tempIn, StandardCopyOption.REPLACE_EXISTING);
                }

                compress(tempIn, tempOut);

                // This is the check for seeing if the compression
                // actually helped to make it smaller
                if(file.getSize() > Files.size(tempOut)){
                    Files.copy(tempOut, finalPath, StandardCopyOption.REPLACE_EXISTING);
                }else{
                    Files.copy(tempIn, finalPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                Files.deleteIfExists(tempIn);
                Files.deleteIfExists(tempOut);
            }
        }else{
            // Now this is for normal files like
            // images, texts, scripts


            Files.copy(file.getInputStream(), finalPath, StandardCopyOption.REPLACE_EXISTING);
        }



        SavePost(postNumber, userPostNumber, userhandle, finalPath.toString(),savedPath,org_filename, user, description);

    }




    private boolean isVideoFile(String fileName) {

        String lower =
                fileName.toLowerCase();

        return lower.endsWith(".mp4")
                || lower.endsWith(".mov")
                || lower.endsWith(".mkv")
                || lower.endsWith(".webm")
                || lower.endsWith(".avi");
    }



    private void SavePost(int postNumber, int userPostNumber, String userHandle, String folderPath,String savedPath, String org_name, User user, String desc){

        try{
            Path pathforpostfile = Paths.get(folderPath);
            Path pathforinteractionfile = Paths.get(savedPath,"interaction.txt");
            PostDirectories paths = new PostDirectories(pathforpostfile.toString(), pathforinteractionfile.toString());
            Post post = new Post(postNumber, userHandle, paths);
            post.getPostData().setPostName(org_name);
            post.getPostData().setDescription(desc);
            post.generateSearchTitle();
            postRepository.save(post);
            user.getAccountData().setPostCount(userPostNumber);
            user.getAccountData().setLiteralPostCount(postNumber);
            userService.save(user);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not upload the file");
        }

    }

    @Override
    public void save(Post post){
        postRepository.save(post);
    }

    private void compress(Path in, Path out) throws Exception{
        // Create instances of the FFmpeg tools that I need to
        // use to create a compression system
        FFmpeg ffmpeg = new FFmpeg("C:/ffmpeg/bin/ffmpeg.exe");
        FFprobe ffprobe = new FFprobe("C:/ffmpeg/bin/ffprobe.exe");

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in.toString())
                .overrideOutputFiles(true)
                .addOutput(out.toString())
                .setVideoCodec("libx264")
                .addExtraArgs("-crf", "23")
                .addExtraArgs("-preset", "medium")
                .setAudioCodec("aac")
                .setAudioBitRate(128_000)
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder).run();
    }

    @Override
    public void deleteBySearchTitle(String searchTitle, String password) throws IOException {
        Post post = postRepository.findBySearchTitle(searchTitle);
        if(post == null) throw  new EntityNotFoundException("Post not found");
        User user = userService.getUserByUserHandle(post.getUserHandle());
        if(user == null) throw new EntityNotFoundException("User not found");
        if(user.getUserInfo().getPassword().equals(password)){
            File folder = new File(STORAGE_PATH + "/" + user.getUserHandle().getUserHandle() + "/post#" + post.getPostNumber());

            System.out.println(
                    "file: " + post.getPostDirectories().getPostPath() + "\n" +
                            "interaction: " + post.getPostDirectories().getInteractionPath() + "\n" +
                            "post: " + folder
            );
//            Files.delete(Paths.get(post.getPostDirectories().getPostPath()).toAbsolutePath());
//            Files.delete(Paths.get(post.getPostDirectories().getInteractionPath()).toAbsolutePath());
//            Files.delete(Paths.get(folder.toURI()).toAbsolutePath());
            // Fix the method name spelling: deleteRecursively instead of deleteRecursivley


            if (folder.exists()) {
                boolean deleted = FileSystemUtils.deleteRecursively(folder);
                if (!deleted) {
                    throw new IOException("Could not delete the post directory.");
                }
            }
            this.postRepository.deleteBySearchTitle(searchTitle);
            user.getAccountData().setPostCount(user.getAccountData().getPostCount() - 1);
            userService.save(user);

        }
    }
}

