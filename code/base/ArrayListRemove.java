package base;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 遍历list的过程中删除元素
 * 最佳实践：倒序遍历
 *
 * 正序遍历因为删除后，数组后面所有的元素都需要向前移，最后一个元素设置为null。
 * 所以如果两个元素连续相同，会导致后一个元素删除不到。
 *
 */
public class ArrayListRemove {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("bb");
        list.add("aa");
        list.add("cc");
        // 删除元素 bb
        remove(list, "bb", 4);
        System.out.println(list);
    }

    private static void remove(ArrayList<String> list, String elem, int code) {

        switch (code) {
            case 1:
                System.out.println("普通for循环正序删除...");
                positiveSequence(list, elem);
                break;
            case 2:
                System.out.println("普通for循环倒序删除");
                negativeSequence(list, elem);
                break;
            case 3:
                System.out.println("增强for循环删除，使用ArrayList的remove()方法删除");
                forEach(list, elem);
                break;
            case 4:
                System.out.println("迭代器，使用ArrayList的remove()方法删除");
                withIterator0(list, elem);
                break;
            case 5:
                System.out.println("迭代器，使用迭代器的remove()方法删除");
                withIterator1(list, elem);
                break;
            default:
                break;
        }
    }

    private static void positiveSequence(ArrayList<String> list, String elem) {
        // 方法一：普通for循环正序删除，删除过程中元素向左移动，不能删除重复的元素
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(elem)) {
                list.remove(list.get(i));
            }
        }
    }

    private static void negativeSequence(ArrayList<String> list, String elem) {
        // 方法二：普通for循环倒序删除，删除过程中元素向左移动，可以删除重复的元素
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).equals(elem)) {
                list.remove(list.get(i));
            }
        }
    }

    private static void forEach(ArrayList<String> list, String elem) {
        // 方法三：增强for循环删除，使用ArrayList的remove()方法删除，产生并发修改异常 ConcurrentModificationException
        for (String str : list) {
            if (str.equals(elem)) {
                list.remove(str);
            }
        }
    }

    private static void withIterator0(ArrayList<String> list, String elem) {
        // 方法四：迭代器，使用ArrayList的remove()方法删除，产生并发修改异常 ConcurrentModificationException
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().equals(elem)) {
                iterator.remove();
            }
        }
    }

    private static void withIterator1(ArrayList<String> list, String elem) {
        // 方法五：迭代器，使用迭代器的remove()方法删除，可以删除重复的元素，但不推荐
        list.removeIf(o -> o.equals(elem));
    }
}
