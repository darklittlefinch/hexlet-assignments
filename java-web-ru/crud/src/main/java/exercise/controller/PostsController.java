package exercise.controller;

import java.util.Collections;
import java.util.List;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void showPost(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var postPage = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("postPage", postPage));
    }

    public static void showAllPosts(Context ctx) {
        var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
        var offset = (page - 1) * per;

        List<Post> sliceOfPosts = PostRepository.getEntities().subList(offset, offset + per);
        var postsPage = new PostsPage(sliceOfPosts, page);
        ctx.render("posts/index.jte", Collections.singletonMap("postsPage", postsPage));
    }
    // END
}
