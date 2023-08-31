@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h1>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</h1>
        <p>ID: ${page.getUser().getId()}</p>
        <p>E-mail: ${page.getUser().getEmail()}</p>
    `,
    footer = @`
        <a href="https://github.com/darklittlefinch">My GitHub</a>
    `
)
