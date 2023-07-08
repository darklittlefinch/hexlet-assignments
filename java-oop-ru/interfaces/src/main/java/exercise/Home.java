package exercise;

// BEGIN
public interface Home {
    double getArea();

    default int compareTo(Home another) {
        var area1 = getArea();
        var area2 = another.getArea();

        return Double.compare(area1, area2);
    }
}
// END
