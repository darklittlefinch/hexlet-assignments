package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    public static int getCountOfFreeEmails(List<String> emailsList) {
        String[] freeDomains = {"gmail.com", "yandex.ru", "hotmail.com"
        };

        List<String> freeDomainsList = Arrays.asList(freeDomains);

        int count = (int) emailsList.stream()
                .map(email -> email.split("@"))
                .filter(email -> freeDomainsList.contains(email[1]))
                .count();

        return count;
    }
}
// END
