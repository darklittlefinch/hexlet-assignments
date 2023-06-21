package exercise;

import java.util.Arrays;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] image2 = Arrays.stream(image)
                .map(rows -> Arrays.stream(rows)
                        .flatMap(item -> Arrays.stream(new String[] {item, item}))
                        .toArray(String[]::new))
//                .map(rows -> Arrays.stream(rows)
//                        .map(item -> item)
//                        .map(item -> item))
                .toArray(String[][]::new);

        String[][] image3 = new String[image.length * 2][image[0].length * 2];

        var i = 0;
        var j = 0;

        while (i < image3.length) {
            image3[i] = image2[j];
            i++;
            image3[i] = image2[j];
            i++;
            j++;
        }

        return image3;
    }
}
// END
