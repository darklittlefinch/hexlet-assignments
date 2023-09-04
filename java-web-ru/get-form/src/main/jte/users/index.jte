@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="en">
    <head>
        <title>Users</title>
    </head>
    <body>
        <form action="/users" method="get">
            <input type="search" name="term" value="${page.getTerm()}">
            <input type="submit" value="Search">
        </form>

        @for(var user: page.getUsers())
            <h1>${user.getFirstName()} ${user.getLastName()}</h1>
            <p>${user.getEmail()}</p>
        @endfor
    </body>
</html>
