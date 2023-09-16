@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    page = page,
    content = @`
        @for(var post: page.getPosts())
            <h2>${post.getName()}</h2>
            <p>${post.getBody()}</p>
        @endfor
    `
)
