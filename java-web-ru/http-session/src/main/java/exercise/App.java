package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            List<Map<String, String>> usersPerPage = new ArrayList<>();

            var first = (page - 1) * per;
            var last = first + (per - 1);

            if (last > USERS.size()) {
                last = USERS.size();
            }

            for (var i = first; i <= last; i++) {
                usersPerPage.add(USERS.get(i));
            }

            ctx.json(usersPerPage);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
