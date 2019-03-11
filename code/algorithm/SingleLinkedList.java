package algorithm;

public class SingleLinkedList {
    /**
     * 默认链表
     */
    private Node head;

    /**
     * 链表长度/大小
     */
    private int size;


    public SingleLinkedList() {
        this.head = new Node(null);
        this.size = 0;
    }

    /**
     * 链表反转-非递归 带头结点
     */
    private void reverse() {
        Node current = head.next; // head.next 首元结点
        Node prev = null; // 前驱结点
        Node next = null; // 后驱结点

        while (current != null) {
            next = current.next;
            // 到达链表尾部（也就是反转后链表的首元结点），头结点指向首元结点
            if (next == null) {
                head.next = current;
            }

            current.next = prev; // 当前结点指向其前驱结点
            prev = current; // 当前结点变为前驱结点，为下一结点转换做准备
            current = next; // 当前结点移动至下一个结点
        }
    }

    /**
     * 链表反转-递归 无头结点
     *
     * @param head 原链表
     * @return 反转后的链表
     */
    private Node reverse(Node head) {
        if(head == null || head.next == null){
            return head;
        }

        Node node = reverse(head.next);

        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 尾插法
     *
     * @param data 待插入数据
     */
    private void append(Object data) {
        Node newNode = new Node(data);

        Node temp = head;
        while (temp.next !=null) {
            temp = temp.next;
        }

        temp.next = newNode;
        this.size++;
    }

    /**
     * 遍历链表
     */
    private void traverse() {
//        log.info("traverse...");
        Node node = head.next; // 有头结点

        while (node != null) {
            System.out.println(node.data.toString());
            node = node.next;
        }
    }

    /**
     * 初始化链表数据
     */
    private void init() {
        for (int i = 0; i < 10; i++) {
            append(i);
        }
    }

    public void test() {
        init();
//        head = reverse(head.next);
        reverse();
        System.out.println(head);
        traverse();
    }

    private static class Node {
        Object data;
        Node next;

        Node (Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node (Object data) {
            this.data = data;
        }
    }
}
