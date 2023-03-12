package flashcards;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Flashcards {
    private final ArrayList<Card> dict;
    private ArrayList<Card> hardestCards;
    private int numberOfHardestCard;
    private int maxMistakesCount;



    public Flashcards() {
        dict = new ArrayList<>();
        numberOfHardestCard = 0;
        maxMistakesCount = 0;
    }

    public void addCard(Scanner sc) {

        CommandLinesTexts.inputCard();
        String card = CommandLinesTexts.recordInput(sc);

        if (isContainTerm(card)) {
            CommandLinesTexts.cardExits(card);
            return;
        }

        CommandLinesTexts.inputDefi();
        String defi = CommandLinesTexts.recordInput(sc);

        if (isContainDefi(defi)) {
            CommandLinesTexts.defiExits(defi);
            return;
        }

        dict.add(new Card(card, defi));
        CommandLinesTexts.pairAdded(card, defi);
    }

    public void removeCard(Scanner sc) {
        CommandLinesTexts.cardToRemove();
        String card = CommandLinesTexts.recordInput(sc);
        if (!isContainTerm(card)) {
            CommandLinesTexts.cannotRemove(card);
        } else {
            remove(card);
            CommandLinesTexts.successRemove();
        }
    }

    public String getFileNameFromUser(Scanner sc) {
        CommandLinesTexts.askFileName();
        String pathToFile = CommandLinesTexts.recordInput(sc);
        return pathToFile;
    }

    public void importCard(String pathToFile) {
        int count = 0;

        File file = new File(pathToFile);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String card = scanner.nextLine();
                String defi = scanner.nextLine();
                int mistakeCounts = Integer.parseInt(scanner.nextLine());
                addCardToDict(new Card(card, defi, mistakeCounts));
                count++;
            }
        } catch (FileNotFoundException e) {
            CommandLinesTexts.fileNotFound();
        } catch (InputMismatchException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
        if (count > 0) {
            CommandLinesTexts.successLoaded(count);
        }
    }

    public void exportCard(String pathToFile) {
        int count = 0;

        File file = new File(pathToFile);

        try (PrintWriter printWriter = new PrintWriter(file)) {

            for (int i = 0; i < dict.size(); i++) {
                String card = dict.get(i).getTerm();
                String defi = dict.get(i).getDefi();
                int missCounts = dict.get(i).getMisCount();

                printWriter.println(card);
                printWriter.println(defi);
                printWriter.println(missCounts);

                count++;
            }

        } catch (IOException e) {
            CommandLinesTexts.fileNotFound();
        }

        if (count > 0) {
            CommandLinesTexts.successSaved(count);
        }
    }

    public void ask(Scanner sc) {
        CommandLinesTexts.askTimesToAsk();

        int times = CommandLinesTexts.recordInputInt(sc);
        sc.nextLine();

        for (int i = 0; i < dict.size(); i++) {

            if (times == 0) {
                return;
            }

            String card = dict.get(i).getTerm();
            String defi = dict.get(i).getDefi();

            CommandLinesTexts.askDefi(card);
            String input = CommandLinesTexts.recordInput(sc);


            if (input.equals(defi)) {
                CommandLinesTexts.correctAnswer();
            } else {

                dict.get(i).increaseError();
                String correctTerm = "";

                for (int j = 0; j < dict.size(); j++) {
                    String defiInDict = dict.get(j).getDefi();
                    if (defiInDict.equalsIgnoreCase(input)) {
                        correctTerm = dict.get(j).getTerm();
                        break;
                    }
                }
                if (correctTerm.isEmpty()) {
                    CommandLinesTexts.wrongAnswer(defi);

                } else {
                    CommandLinesTexts.wrongAnswerCorrectForOther(defi, correctTerm);
                }

            }
            times -= 1;
        }
    }

    public boolean isContainTerm(String card) {
        for (Card c: dict) {
            if (c.getTerm().equals(card)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContainDefi(String defi) {
        for (Card c : dict) {
            if (c.getDefi().equals(defi)) {
                return true;
            }
        }
        return false;
    }

    public void remove(String card) {
        Iterator<Card> iterator = dict.iterator();
        while (iterator.hasNext()) {
            Card c = iterator.next();
            if (c.getTerm().equals(card)) {
                iterator.remove();
            }
        }
    }


    public void applicationLog(Scanner sc) {
        CommandLinesTexts.askFileName();
        String fileName = CommandLinesTexts.recordInput(sc);

        File log = new File(fileName);

        try(PrintWriter writer = new PrintWriter(log)) {
            for (int i = 0; i < CommandLinesTexts.getLog().size(); i++) {
                writer.println(CommandLinesTexts.getLog().get(i));
            }

            writer.println("  ");
            writer.println("The log has been saved.");

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = now.format(formatter);
            writer.println("Date: " + formattedDate);

        } catch (IOException e) {
            CommandLinesTexts.failToSaveLog();
        }

        CommandLinesTexts.savedLog();

    }


    public void resetStats() {
        for (int i = 0; i < dict.size(); i++) {
            dict.get(i).setMisCount(0);
        }
        CommandLinesTexts.resetStats();
    }


    public void hardestCard() {
        this.updateHardest();

        if (numberOfHardestCard == 0) {
            CommandLinesTexts.noCardWithErrors();
        } else if (numberOfHardestCard == 1) {
            String card = hardestCards.get(0).getTerm();
            CommandLinesTexts.oneHardestCard(card, getMaxMistakesCount());
        } else {
            CommandLinesTexts.multipleHardestCard(getHardestCardNames(), getMaxMistakesCount());
        }

    }

    private ArrayList<String> getHardestCardNames() {
        ArrayList<String> cardNames = new ArrayList<>();
        for (Card c : hardestCards) {
            cardNames.add(c.getTerm());
        }
        return cardNames;
    }

    private int getMaxMistakesCount() {
        for (Card c : dict) {
            if (c.getMisCount() > maxMistakesCount) {
                this.maxMistakesCount = c.getMisCount();
            }
        }
        return maxMistakesCount;
    }

    private void updateHardest() {
        hardestCards = new ArrayList<>();

        int max = this.getMaxMistakesCount();
        for (Card c : dict) {
            if (c.getMisCount() == max) {
                hardestCards.add(c);
            }
        }
        numberOfHardestCard = hardestCards.size();
    }

    private void addCardToDict(Card newCard) {
        for (int i = 0; i < dict.size(); i++) {
            if (dict.get(i).getTerm().equals(newCard.getTerm())) {
                dict.set(i, newCard);
                return; // Break out of the loop once the card has been added
            }
        }
        dict.add(newCard);
    }

    public void exitFlashCards(boolean exportFile, String pathToFile) {
        if (exportFile) {
            this.exportCard(pathToFile);
        }
        CommandLinesTexts.exitMessage();
    }
}
