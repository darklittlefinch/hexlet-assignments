@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

<!-- BEGIN -->
<html lang="ru">
    <body>
        @if(page.getName() != null)
            Welcome, ${page.getName().toString()}
            <form action="${NamedRoutes.logoutPath()}" method="post">
                <button type="submit" name="logout">Выйти</button>
            </form>
        @endif
        @if(page.getName() == null)
            <form action="${NamedRoutes.buildSessionPath()}">
                <button type="submit" name="logout">Войти</button>
            </form>
        @endif
    </body>
</html>
<!-- END -->
