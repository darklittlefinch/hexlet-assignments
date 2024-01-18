package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        System.out.println("Creating SafetyList instance...");
        var list = new SafetyList();

        var thread1 = new ListThread(list);
        var thread2 = new ListThread(list);

        System.out.println(thread1.getName() + " is starting...");
        thread1.start();

        System.out.println(thread2.getName() + " is starting...");
        thread2.start();

        System.out.println("DONE!");
        // END
    }
}

