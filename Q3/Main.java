import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int line, size;
            String[] s;
            line = Integer.parseInt(bufferedReader.readLine());
            PriorityQueue<LinkedList> sorted = new PriorityQueue<>(new Comparator<LinkedList>() {
                @Override
                public int compare(LinkedList linkedList, LinkedList t1) {
                    if (linkedList.start.value == t1.start.value)
                        return 0;
                    if (linkedList.start.value > t1.start.value)
                        return 1;
                    return -1;
                }
            });
            for (int i = 0; i < line; i++) {
                s = bufferedReader.readLine().split(" ");
                LinkedList list = new LinkedList();
                for (int j = 1; j < s.length; j++) {
                    list.add(Integer.parseInt(s[j]));
                }
                sorted.add(list);
            }
            while (!sorted.isEmpty()) {
                LinkedList list = sorted.poll();
                bufferedWriter.write(String.valueOf(list.start.value) + " ");
                if (list.start.next != null) {
                    list.start = list.start.next;
                    sorted.add(list);
                }
            }
            bufferedWriter.flush();
        } catch (Exception e) {

        }
    }
}

class LinkedList {
    Node start;
    Node end;

    public LinkedList() {
        start = null;
        end = null;
    }

    public void add(int number) {
        if (start == null) {
            start = new Node(number);
            end = start;
        } else {
            Node node = new Node(number);
            end.next = node;
            node.pre = end;
            end = end.next;
        }
    }
}

class Node {
    Node next;
    Node pre;
    int value;

    public Node(int value) {
        this.value = value;
        pre = null;
        next = null;
    }
}