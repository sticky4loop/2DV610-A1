package Mastermind.view;

import Mastermind.model.*;

import java.io.PrintStream;
import java.util.*;

public class View {

    private PrintStream out;

    public final static String WELCOME = "Welcome to a game of Mastermind";
    public final static String INSTRUCTIONS = View.getMenuText();
    public final static String QUIT = "Thank you for playing Mastermind and welcome back anytime";
    public final static String CONGRATS = "Well done! You found the secret code :)";

    public View(PrintStream output) {
        this.out = output;
    }

    public void showWelcomeMessage() {
        out.println(this.WELCOME);
    }

    public void showInstructions() {
        out.println(this.INSTRUCTIONS);
    }

    public void showBoard(Board board) {
        out.println(getBoardGraphics(board));
    }

    public void showQuitMessage() {
        out.println(this.QUIT);
    }

    public String getBoardGraphics(Board board) {
        List<String> result = new ArrayList<>();

        board.getGuessHistory().forEach(guess -> result.add(guess.toString()));

        while (result.size() < Board.DEFAULT_TABLE_LENGTH) {
            result.add(new String(new char[Board.DEFAULT_ROW_LENGTH]).replace("\0", "_ "));
        }

        if (board.getSecretCode() != null) {
            result.add(board.getSecretCode().toString());
        } else {
            result.add(new String(new char[Board.DEFAULT_ROW_LENGTH]).replace("\0", "? "));
        }

        Collections.reverse(result);

        return String.join("\n", result);
    }

    public void showCongratulations() {
        out.println(this.CONGRATS);
    }

    public Row getUserGameInput(Scanner sc) throws Exception {
        String value;

        do {
            value = sc.nextLine();
        } while (!SymbolPeg.isValidInput(value));

        return new Row(SymbolPeg.getByString(value));
    }
    
    private static String getMenuText() {
        String output = "\nInstructions:\nEnter your guess. For example: \"dhcs\".\n";

        List<String> shortcuts = new ArrayList<>();
        String shortcut;
        List<String> symbolName = new ArrayList<>();

        for (SymbolPeg symbolPeg : SymbolPeg.values()) {
            shortcut = symbolPeg.name().toLowerCase().charAt(0) + "";
            shortcuts.add(shortcut);
            symbolName.add("(" + shortcut + ")" + symbolPeg.name().substring(1));
        }

        output += String.join(" ,", symbolName);
        output += "\n";

        return output;
    }
}
