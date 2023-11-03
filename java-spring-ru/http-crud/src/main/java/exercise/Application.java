package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1") int page) {
        return posts.stream()
                .skip((long) limit * (page - 1))
                .limit(limit)
                .toList();
    }

    @GetMapping("/posts/{id}")
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(value -> value.getId().equals(id))
                .findFirst();
        return post;
    }

    @PostMapping("/posts")
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var possiblePost = posts.stream()
                .filter(value -> value.getId().equals(id))
                .findFirst();
        if (possiblePost.isPresent()) {
            var post = possiblePost.get();
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(value -> value.getId().equals(id));
    }
    // END
}
