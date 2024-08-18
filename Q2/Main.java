import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        LinkedList users=new LinkedList();
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();
        String ip;
        String username;
        long money;
        String[] strings;
        Pattern pattern=Pattern.compile("[#$_*]+");
        Matcher matcher;
        int number =0;
        for (int i = 0; i < counter; i++) {
            switch (scanner.nextInt()) {
                case 1: {
                    strings =scanner.nextLine().split(":");
                    username =strings[1];
                    matcher =pattern.matcher(username);
                    if (matcher.find()) {
                        System.out.println("invalid username");
                    } else {
                        ip = strings[0];
                        users.add(username, ip);
                    }
                }
                break;
                case 2: {
                    strings =scanner.nextLine().split(":");
                    ip =strings[0];
                    username =strings[1];
                    money =Long.parseLong(strings[2]);
                    number =0;
                    users.increaseMoneyWithUsername(username , money);
                    users.decreaseMoneyWithip(ip , money);
                }
                break;
                case 3: {
                    ip =scanner.nextLine();
                    users.printMoneyWithIp(ip);
                }
            }
        }
    }
}

class User {
    String username;
    String ip;
    long money;

    public User(String username, String ip) {
        this.username = username;
        this.ip = ip;
        this.money =0;
    }
}

class Node{
    Node next;
    Node pre;
    User user;

    public Node(User user){
        this.user =user;

    }
}

class LinkedList{
    Node start;
    Node end;

    public LinkedList(){
        start =null;
        end=null;
    }

    public void add(String username , String ip){
        if (start == null){
            start =new Node(new User( username, ip));
            end =start;
        }else {
            Node node =new Node(new User(username, ip));
            end.next =node;
            node.pre =end;
            end =end.next;
        }
    }

    public void increaseMoneyWithUsername(String username , long money){
        Node node =this.start;
        if (node.user.username.equals(username)){
            node.user.money+=money;
            return;
        }
        while (node.next!= null){
            node =node.next;
            if (node.user.username.equals(username)){
                node.user.money+=money;
                return;
            }
        }
    }

    public void decreaseMoneyWithip(String ip , long money){
        Node node =this.start;
        if (node.user.ip.equals(ip)){
            node.user.money-=money;
            return;
        }
        while (node.next!= null){
            node =node.next;
            if (node.user.ip.equals(ip)){
                node.user.money-=money;
                return;
            }
        }
    }

    public void printMoneyWithIp(String ip){
        Node node =this.start;
        if (node.user.ip.equals(ip)){
            System.out.println(node.user.money);
            return;
        }
        while (node.next!= null){
            node =node.next;
            if (node.user.ip.equals(ip)){
                System.out.println(node.user.money);
                return;
            }
        }
    }

    public void toStringg(){
        Node node =start;
        while (node.next != null) {
            System.out.println(node.user.username);
            node =node.next;
        }
    }
}