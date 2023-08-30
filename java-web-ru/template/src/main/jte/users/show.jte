@import exercise.dto.users.UserPage
@param UserPage userPage

<html lang="en">
    <head>
        <title>Hexlet</title>
    </head>
    <body>
        <h1>User</h1>
        <p>ID: ${userPage.getUser().getId()}</p>
        <p>Name: ${userPage.getUser().getFirstName()} ${userPage.getUser().getLastName()}</p>
        <p>E-mail: ${userPage.getUser().getEmail()}</p>
    </body>
</html>
