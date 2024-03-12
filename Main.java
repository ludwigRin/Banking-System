import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        User user1 = new User(12345, "Ludwig", "123");
        
        Account acc = new Account(1, "basic", user1);
        Account acc1 = new Account(2, "advanced", user1);
        acc.deposit(100);
        acc1.deposit(100);
        acc.withdraw(25);
        

        System.out.println(user1.getAccounts());
        System.out.println(user1.getBalance());

    }
}