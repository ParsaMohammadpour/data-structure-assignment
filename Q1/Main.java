import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int counter =scanner.nextInt();
        MedianFinder queue =new MedianFinder();
        for (int i = 0; i < counter; i++) {
            queue.add(scanner.nextInt());
            queue.print();
        }
    }
}


class MedianFinder{
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    public MedianFinder(){
        max =new PriorityQueue<>(Comparator.naturalOrder());
        min =new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void add(int number){
        if (min.size() == max.size()) {
            if (min.size() != 0 && max.peek() < number){
                min.add(max.remove());
                max.add(number);
            }else {
                min.add(number);
            }
        } else {
            if (min.peek() > number) {
                max.add(min.remove());
                min.add(number);
            }else {
                max.add(number);
            }
        }
    }

    public void print(){
        double median =0.0;
        if (min.size() == max.size()) {
            median =((double) min.peek() + (double) max.peek()) / 2.0;
        }else {
            median =(double) min.peek();
        }
        System.out.printf("%.1f\n",median);
    }
}