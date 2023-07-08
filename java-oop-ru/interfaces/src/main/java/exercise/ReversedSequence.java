package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    private String string;

    public ReversedSequence(String string) {
        this.string = string;
    }

    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public String getString() {
        return reverse(string);
    }

    public String toString() {
        return getString();
    }

    @Override
    public int length() {
        return getString().length();
    }

    @Override
    public char charAt(int i) {
        return getString().charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return getString().subSequence(i, i1);
    }
}
// END
