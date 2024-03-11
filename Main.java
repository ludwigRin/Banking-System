import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        User user1 = new User(12345, "Ludwig", "123");
        Account acc1 = new Account(1, "basic", user1);

        user1.changePassword();

        
        
        System.out.println(user1.getAccounts());
        
    }



}