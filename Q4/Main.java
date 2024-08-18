import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(System.out))) {
            PriorityQueue<User> higher = new PriorityQueue<>(new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    if (user.submitNumber > t1.submitNumber)
                        return 1;
                    if (user.submitNumber < t1.submitNumber)
                        return -1;
                    if (user.penalty == t1.penalty)
                        return 0;
                    if (user.penalty < t1.penalty)
                        return 1;
                    return -1;
                }
            });
            PriorityQueue<User> lower = new PriorityQueue<>(new Comparator<User>() {
                @Override
                public int compare(User user, User t1) {
                    if (user.submitNumber > t1.submitNumber)
                        return -1;
                    if (user.submitNumber < t1.submitNumber)
                        return 1;
                    if (user.penalty == t1.penalty)
                        return 0;
                    if (user.penalty < t1.penalty)
                        return -1;
                    return 1;
                }
            });
            int lines, penalty, std_number;
            String []a;
            a =reader.readLine().split(" ");
            User[] users = new User[Integer.parseInt(a[0]) + 1];
            users[1] =new User(1 , 0 , true);
            users[1].submitNumber =0;
            users[1].penalty =0;
            lines =Integer.parseInt(a[1]);
            for (int i = 0; i < lines; i++) {
                a =reader.readLine().split(" ");
                std_number = Integer.parseInt(a[0]);
                penalty = Integer.parseInt(a[1]);
                if (std_number == 1) {
                    users[1].refreshPenalty(penalty);
                    while (higher.peek() != null && ((higher.peek().submitNumber < users[1].submitNumber) ||
                            (higher.peek().submitNumber == users[1].submitNumber && higher.peek().penalty >= users[1].penalty))){
                        higher.peek().isLower =true;
                        lower.add(higher.poll());
                    }
                } else if (users[std_number] == null) {
                    if (users[1].submitNumber > 1){
                        users[std_number] =new User(std_number , penalty , true);
                        lower.add(users[std_number]);
                    }else if (users[1].submitNumber < 1){
                        users[std_number] =new User(std_number , penalty , false);
                        higher.add(users[std_number]);
                    }else {
                        if (penalty >= users[1].penalty){
                            users[std_number] =new User(std_number , penalty , true);
                            lower.add(users[std_number]);
                        }else {
                            users[std_number] =new User(std_number , penalty , false);
                            higher.add(users[std_number]);
                        }
                    }
                } else {
                    if (users[std_number].isLower)
                        lower.remove(users[std_number]);
                    else
                        higher.remove(users[std_number]);
                    users[std_number].refreshPenalty(penalty);
                    if (users[std_number].submitNumber < users[1].submitNumber){
                        lower.add(users[std_number]);
                        users[std_number].isLower =true;
                    }else if (users[std_number].submitNumber > users[1].submitNumber){
                        higher.add(users[std_number]);
                        users[std_number].isLower =false;
                    }else {
                        if (users[std_number].penalty >= users[1].penalty){
                            lower.add(users[std_number]);
                            users[std_number].isLower =true;
                        }else {
                            higher.add(users[std_number]);
                            users[std_number].isLower =false;
                        }
                    }
                }
                writer.write(Integer.toString(higher.size() + 1) + "\n" );
                writer.flush();
            }
        }catch (Exception e){}
    }
}

class User {
    int std_id;
    int penalty;
    boolean isLower;
    int submitNumber;

    public User(int std_id, int penalty, boolean isLower) {
        this.std_id = std_id;
        this.penalty = penalty;
        this.isLower = isLower;
        this.submitNumber = 1;
    }

    void refreshPenalty(int penalty) {
        this.penalty +=penalty;
        this.submitNumber++;
    }
}