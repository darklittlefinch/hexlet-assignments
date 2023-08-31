@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        @for(var user: page.getUsers())
            <h1>${user.getId()}. ${user.getFirstName()} ${user.getLastName()}</h1>
            <p>E-mail: ${user.getEmail()}</p>
        @endfor
    `,
    footer = @`
        <a href="https://github.com/darklittlefinch">My GitHub</a>
    `
)
