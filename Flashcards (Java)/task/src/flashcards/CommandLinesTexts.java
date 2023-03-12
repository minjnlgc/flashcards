package flashcards;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandLinesTexts {

    final private static ArrayList<String> log = new ArrayList<>();

    /*Action requests. */
    public static void inputCard() {
        System.out.println("The card:");
        log.add("The card:");
    }

    public static void inputDefi() {
        System.out.println("The definition of the card:");
        log.add("The definition of the card:");
    }
    public static void cardToRemove() {
        System.out.println("Which card?");
        log.add("Which card?");
    }

    public static void askFileName() {
        System.out.println("File name:");
        log.add("File name:");
    }

    public static void askTimesToAsk() {
        System.out.println("How many times to ask?");
        log.add("How many times to ask?");
    }

    /*Action responses. */
    public static void cardExits(String card) {
        String message = String.format("The card \"%s\" already exists.", card);
        System.out.println(message);
        log.add(message);
    }

    public static void defiExits(String defi) {
        String message = String.format("The definition \"%s\" already exists.", defi);
        System.out.println(message);
        log.add(message);
    }

    public static void pairAdded(String card, String defi) {
        String message = String.format("The pair (\"%s\":\"%s\") has been added.\n", card, defi);
        System.out.println(message);
        log.add(message);
    }

    public static void cannotRemove(String card) {
        String message = String.format("Can't remove \"%s\": there is no such card.\n", card);
        System.out.println(message);
        log.add(message);
    }

    public static void successRemove() {
        System.out.println("The card has been removed.");
        log.add("The card has been removed.");

    }

    public static void fileNotFound() {
        System.out.println("File not found.");
        log.add("File not found.");
    }


    public static void successLoaded(int count) {
        String message = String.format("%d cards have been loaded.", count);
        System.out.println(message);
        log.add(message);
    }

    public static void successSaved(int count) {
        String message = String.format("%d cards have been saved.", count);
        System.out.println(message);
        log.add(message);
    }

    public static void askDefi(String card) {
        String message = String.format("Print the definition of \"%s\":", card);
        System.out.println(message);
        log.add(message);
    }

    public static void correctAnswer() {
        System.out.println("Correct!");
        log.add("Correct!");
    }

    public static void wrongAnswer(String defi) {
        String message = String.format("Wrong. The right answer is \"%s\".", defi);
        System.out.println(message);
        log.add(message);
    }

    public static void wrongAnswerCorrectForOther(String defi, String correctTerm) {
        String message = String.format("Wrong. The right answer is \"%s\", " +
                "but your definition is correct for \"%s\".", defi, correctTerm);
        System.out.println(message);
        log.add(message);
    }

    public static void resetStats() {
        System.out.println("Card statistics have been reset.");
        log.add("Card statistics have been reset.");
    }

    public static void failToSaveLog() {
        System.out.println("Failed to save the log");
        log.add("Failed to save the log");
    }

    public static void exitMessage() {
        System.out.println("Bye bye!");
        log.add("Bye bye!");
    }

    public static void invalidActon() {
        System.out.println("Invalid action.");
        log.add("Invalid action.");
    }

    public static void askForAction() {
        System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
        log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
    }

    /* Hardest card command lines outputs. */
    public static void noCardWithErrors() {
        System.out.println("There are no cards with errors.");
        log.add("There are no cards with errors.");
    }

    public static void oneHardestCard(String card, int maxErrors) {
        String message = String.format("The hardest card is \"%s\". You have %d errors answering it.", card, maxErrors);
        System.out.println(message);
        log.add(message);
    }

    public static void multipleHardestCard(ArrayList<String> cardNames, int maxErrors) {
        String names = "";
        for (int i = 0; i < cardNames.size(); i++) {
            if (i == cardNames.size() - 1) {
                names += String.format("\"%s\"", cardNames.get(i));
            } else {
                names += String.format("\"%s\", ", cardNames.get(i));
            }
        }

        String message = String.format("The hardest cards are %s. You have %d errors answering them.", names, maxErrors);
        System.out.println(message);
        log.add(message);
    }


    /* Recode the input to log. */
    public static String recordInput(Scanner sc) {
        String input = sc.nextLine();
        log.add(input);
        return input;
    }

    public static int recordInputInt(Scanner sc) {
        int input = sc.nextInt();
        log.add("" + input);
        return input;
    }

    public static void savedLog() {
        System.out.println("The log has been saved.");
        log.add("The log has been saved.");
    }

    public static ArrayList<String> getLog() {
        return log;
    }


}
