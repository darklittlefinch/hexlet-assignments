package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> elements1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        int count1 = 2;
        List<Integer> actual1 = App.take(elements1, count1);
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));
        assertThat(actual1).isEqualTo(expected1);

        List<Integer> elements2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        int count2 = 8;
        List<Integer> actual2 = App.take(elements2, count2);
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        assertThat(actual2).isEqualTo(expected2);
        // END
    }
}
