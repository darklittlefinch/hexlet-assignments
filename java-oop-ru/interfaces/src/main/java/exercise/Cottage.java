package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public String toString() {
        String template = "%d этажный коттедж площадью %.1f метров";
        return String.format(template, floorCount, area);
    }
}
// END
