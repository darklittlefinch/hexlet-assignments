package exercise;

// BEGIN
public class Flat implements Home {

    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getLivingArea() {
        return area;
    }

    public double getBalconyArea() {
        return balconyArea;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public double getArea() {
        return getLivingArea() + getBalconyArea();
    }

    public String toString() {
        String template = "Квартира площадью %.1f метров на %d этаже";
        return String.format(template, getArea(), floor);
    }
}
// END
