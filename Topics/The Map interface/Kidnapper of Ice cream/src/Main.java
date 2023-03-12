import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] news = scanner.nextLine().split(" ");
        String[] note = scanner.nextLine().split(" ");

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        for (String word : news) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }

        for (String word : note) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }

        for (String word : note) {
            if (!map1.containsKey(word) || !map2.containsKey(word) || map1.get(word) < map2.get(word)) {
                System.out.println("You are busted");
                return;
            }
        }
        System.out.println("You get money");

    }
}