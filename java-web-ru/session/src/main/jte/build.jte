@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

<!-- BEGIN -->
@if(page.getError() != null)
    ${page.getError()}
@endif

<form action="${NamedRoutes.loginPath()}" method="post">
    <div>
        <label>
            Your name
            <input type="text" name="name" required value="${page.getName()}">
        </label>
        <label>
            Your password
            <input type="password" name="password" required>
        </label>
        <input type="submit" name="confirm">
    </div>
</form>
<!-- END -->
