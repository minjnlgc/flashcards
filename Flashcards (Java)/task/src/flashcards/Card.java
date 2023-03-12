package flashcards;

public class Card {
    String term;
    String defi;
    int misCount;


    public Card(String term, String defi) {
        this.term = term;
        this.defi = defi;
        this.misCount = 0;
    }

    public Card(String term, String defi, int misCount) {
        this.term = term;
        this.defi = defi;
        this.misCount = misCount;
    }

    public String getTerm() {
        return term;
    }

    public String getDefi() {
        return defi;
    }

    public int getMisCount() {
        return misCount;
    }

    public void setMisCount(int misCount) {
        this.misCount = misCount;
    }

    public void decreaseError() {
        this.misCount--;
    }

    public void increaseError() {
        this.misCount++;
    }
}
