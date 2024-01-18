package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (var i = 0; i < 1000; i++) {
            list.add(i);
            System.out.println("Adding: " + i);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
// END
