@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage postPage

@template.layout.page(
    content = @`
        <h1>${postPage.getPost().getName()}</h1>
        <p>${postPage.getPost().getBody()}</p>
        <a href="${NamedRoutes.postsPath()}">To all posts</a>
    `
)
