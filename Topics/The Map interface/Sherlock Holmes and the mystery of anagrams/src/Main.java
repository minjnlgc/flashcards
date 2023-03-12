import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        if (str1.length() != str2.length()) {
            System.out.println("no");
            return;
        }

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for (int i = 0; i < str1.length(); i++) {
            map1.put(str1.charAt(i), map1.getOrDefault(str1.charAt(i), 0) + 1);
        }

        for (int j = 0; j < str2.length(); j++) {
            map2.put(str2.charAt(j), map2.getOrDefault(str2.charAt(j), 0) + 1);
        }

        if (map1.equals(map2)) {
            System.out.print("yes");
        } else {
            System.out.print("no");
        }
    }
}