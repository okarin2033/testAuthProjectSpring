import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String inp = scanner.nextLine();
        System.out.println(reverseStringWithCharAt(inp));

        long m = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++){
            reverseStringWithCharAt(inp);
        }
        System.out.print(System.currentTimeMillis()-m+" ");
        for (int i = 0; i < 10000; i++){
            reverseStringWithCharAt(inp);
        }
        System.out.print(System.currentTimeMillis()-m+" ");
        for (int i = 0; i < 100000; i++){
            reverseStringWithCharAt(inp);
        }
        System.out.print(System.currentTimeMillis()-m+" ");
    }


    public static String reverseStringWithCharAt(String inputString) {
        int stringLength = inputString.length();
        String result = "";
        for (int i = 0; i < stringLength; i++) {
            result = inputString.charAt(i) + result;
        }
        return result;
    }
}
