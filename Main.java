import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        User user1 = new User(12345, "Ludwig", "123");
        
        Account acc = new Account(1, "basic", user1);
    
        user1.addAccount(acc);
        user1.addAccount(acc);
        user1.addAccount(acc);

        System.out.println(user1.getAccounts());
    }
}