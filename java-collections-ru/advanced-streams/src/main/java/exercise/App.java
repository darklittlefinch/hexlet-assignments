package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String config) {

        String[] envs = Arrays.stream(config.split("\n"))
                .filter(str -> str.startsWith("environment="))
                .map(str -> str.replace("environment=", "").replaceAll("\"", ""))
                .flatMap(str -> Arrays.stream(str.split(","))
                                .filter(env -> env.startsWith("X_FORWARDED_"))
                                .map(env -> env.replace("X_FORWARDED_", "")))
                .toArray(String[]::new);

        return String.join(",", envs);


//        var result = new StringJoiner(",");
//
//        String[] configStrings = config.split("\n");
//
//        for (String str: configStrings) {
//            if (str.startsWith("environment=")) {
//                var str2 = str.replace("environment=", "").replaceAll("\"", "");
//                String[] variables = str2.split(",");
//
//                for (String variable: variables) {
//                    if (variable.startsWith("X-FORWARDED_")) {
//                        result.add(variable.replace("X-FORWARDED_", ""));
//                    }
//                }
//
//            }
//        }
//
//        return result.toString();


//                String[] envs = Arrays.stream(config.split("\n"))
//                .filter(str -> str.startsWith("environment="))
//                .map(str -> str.substring(13, str.length()))
//                .map(str -> str.split(","))
//                .map(str -> Arrays.stream(str)
//                        .filter(env -> env.startsWith("X-FORWARDED_"))
//                        .map(env -> env.substring(12)))

    }
}
//END
