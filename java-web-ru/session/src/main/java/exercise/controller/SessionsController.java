package exercise.controller;

import java.util.Collections;
import java.util.List;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.Generator;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    private static final List<User> USERS = Generator.getUsers();
    private static final String CURRENT_USER = "currentUser";

    public static void getMainPage(Context ctx) {
        var page = new MainPage(ctx.sessionAttribute(CURRENT_USER));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        var page = new LoginPage(null, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void login(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> UsersRepository.existsByName(value), "Wrong username")
                    .get();

            var user = UsersRepository.findByName(name);
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> encrypt(value).hashCode() == user.getPassword().hashCode(),
                            "Wrong password")
                    .get();

            ctx.sessionAttribute(CURRENT_USER, name);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (ValidationException e) {
            var user = ctx.formParam("name");
            var page = new LoginPage(user, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute(CURRENT_USER, null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
