@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage postsPage

@template.layout.page(
    content = @`
        @for(var post: postsPage.getPosts())
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
        @endfor
        <a href="${NamedRoutes.postsPath()}?page=${postsPage.getPage() - 1}">Previous page</a>
        <a href="${NamedRoutes.postsPath()}?page=${postsPage.getPage() + 1}">Next page</a>
    `
)
