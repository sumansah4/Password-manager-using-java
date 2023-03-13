import java.util.Random;
import java.util.Scanner;

class PasswordGenerator {
    private static final Random random = new Random();
    private static final String capital="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String small="abcdefghijklmnopqrstuvwxyz";
    private static final String numeric="1234567890";
    private static final String spcl_char="~!@#$%^&*(_+{}|:_[?]>=<";
    private static final String store = capital + small + numeric + spcl_char;

    public void generatePass(int length) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length ; i++) {
            int index = random.nextInt(store.length());
            password.append(store.charAt(index));
        }
        System.out.println(password.toString());
    }
}

public class Main {
    public static void main(String[] args) {
        PasswordGenerator obj = new PasswordGenerator();
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the digit for generating password : ");
        int len = in.nextInt();
        obj.generatePass(len);
        in.close();
    }
}
