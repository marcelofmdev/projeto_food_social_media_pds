package br.edu.ufrn.projeto_food_social_media_pds.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class PostTemporaryDataStorage {

    private final HashMap<Long, Post> dataStorage;

    public PostTemporaryDataStorage() {
        dataStorage = new HashMap<Long, Post>();

        // User 1
        for (int i = 1; i < 3; i++) {
            dataStorage.put(
                    ((long) i),
                    new Post(
                            ((long) i),
                            1L,
                            "/",
                            "contents A" + Integer.toString(i),
                            LocalDate.now()
                    )
            );
        }

        // User 2
        for (int i = 3; i < 5; i++) {
            dataStorage.put(
                    ((long) i),
                    new Post(
                            ((long) i),
                            2L,
                            "/",
                            "contents B" + Integer.toString(i),
                            LocalDate.now()
                    )
            );
        }
    }
    
    public List<Post> getAllPosts() {
        return new ArrayList<Post>(dataStorage.values());
    }

    public Optional<Post> getPostById(Long id) {
        return Optional.ofNullable(dataStorage.get(id));
    }

    public List<Post> getPostsFromUser(Long userId) {
        List<Post> postList = new ArrayList<Post>();

        for(Post post : dataStorage.values()) {
            if(post.getUserId().equals(userId)) {
                postList.add(post);
            }
        }

        return postList;
    }

    public void insertPost(Post post) {
        dataStorage.put(post.getId(), post);
    }

    public void deletePost(Long id) {
        dataStorage.remove(id);
    }
}
