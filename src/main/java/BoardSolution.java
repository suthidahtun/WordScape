import java.util.ArrayList;

public class BoardSolution {

    private ArrayList<String> potentialWords;
    private ArrayList<String> arraySolution;
    private Layout layout;

    public BoardSolution(Layout layout, ArrayList<String> potentialWords) {
        this.potentialWords = potentialWords;
        this.arraySolution = null;
        this.layout = layout;

    }

    public boolean validWord(String word, Word placement) {
        // Determines if a word is valid for a specific placement. Currently depends solely on length of word
        return (word.length() == placement.getWordLength());

    }

    public ArrayList<String> solve() {
        ArrayList<String> startingBoard = new ArrayList<String>();
        return solveBoard(startingBoard, this.layout.getWords());
    }

    public ArrayList<String> solveBoard(ArrayList<String> currentSolution,
                                        ArrayList <Word> remainingLayout) {
        if (remainingLayout.isEmpty()) {
            return currentSolution;
        } else {
            ArrayList<ArrayList<String>> nextBoards = nextBoard(currentSolution, remainingLayout);
            return solveBoardList(nextBoards, removeFirst(remainingLayout));
        }
    }

    public ArrayList<String> solveBoardList(ArrayList<ArrayList<String>> nextBoards, ArrayList<Word> remainingLayout) {
        ArrayList<String> returnedBoard = new ArrayList<String>();
        if (!(nextBoards.isEmpty())) {

            for (ArrayList<String> board : nextBoards) {
                ArrayList<String> check = solveBoard(board, remainingLayout);
                if (!(check.isEmpty())) {
                    return check;
                }
            }
        }
        // if list of boards is empty or none of them produce a non-empty solution
        return returnedBoard;
    }

    public ArrayList<ArrayList<String>> nextBoard(ArrayList<String> currentSolution,
                                                  ArrayList<Word> remainingLayout) {
        ArrayList<ArrayList<String>> nextBoards = new ArrayList<>();
        for (String word : this.potentialWords) {
            if (validWord(word, remainingLayout.get(0)) && !(currentSolution.contains(word))) {
                ArrayList<String> newBoard = new ArrayList<String>();
                newBoard = currentSolution;
                newBoard.add(word);
                nextBoards.add(newBoard);
            }
        }
        return nextBoards;
    }

    public ArrayList<Word> removeFirst(ArrayList<Word> currentLayout) {

        //Removes the first Word from an ArrayList<Word>

        ArrayList<Word> nextLayout = new ArrayList<Word>();
        int count = 1;
        while (count < currentLayout.size()) {
            nextLayout.add(currentLayout.get(count));
            count++;
        }
        return nextLayout;
    }
}

