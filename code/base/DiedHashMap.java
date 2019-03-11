package base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class DiedHashMap implements Serializable {


    private static final long serialVersionUID = 4867966243564520190L;

    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<>(2);

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                new Thread(() -> map.put(UUID.randomUUID().toString(), ""), "ftf" + i).start();
            }
        }, "ftf");

        thread.start();
        thread.join();
        System.out.println(map);
    }
}
