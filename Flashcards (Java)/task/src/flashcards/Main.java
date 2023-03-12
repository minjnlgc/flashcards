package flashcards;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean exitFlashcard = false;
        boolean exportFile = false;
        String pathToFileForExport = "";

        Flashcards flashcards = new Flashcards();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-import")) {
                flashcards.importCard(args[i+1]);
            }
            else if (args[i].equals("-export")) {
                exportFile = true;
                pathToFileForExport = args[i+1];
            }
        }

        while (!exitFlashcard) {
            CommandLinesTexts.askForAction();
            String action = CommandLinesTexts.recordInput(scanner);

            switch (action) {
                case "add" -> flashcards.addCard(scanner);
                case "remove" -> flashcards.removeCard(scanner);
                case "exit" -> {
                    flashcards.exitFlashCards(exportFile, pathToFileForExport);
                    exitFlashcard = true;
                }
                case "import" -> flashcards.importCard(flashcards.getFileNameFromUser(scanner));
                case "export" -> flashcards.exportCard(flashcards.getFileNameFromUser(scanner));
                case "ask" -> flashcards.ask(scanner);
                case "reset stats" -> flashcards.resetStats();
                case "hardest card" -> flashcards.hardestCard();
                case "log" -> flashcards.applicationLog(scanner);
                default -> {
                    CommandLinesTexts.invalidActon();
                }
            }
        }
    }
}
