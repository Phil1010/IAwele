package awele.bot.competitor.negaMax2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import awele.bot.Bot;
import awele.bot.CompetitorBot;
import awele.bot.DemoBot;
import awele.core.Board;
import awele.core.InvalidBotException;

/**
 * @author Alexandre Blansché
 * Bot qui prend ses décisions selon le MinMax
 */
public class NegaMaxBot extends CompetitorBot {
    /** Profondeur maximale */
    private static final int MAX_DEPTH = 5;

    /**
     * @throws InvalidBotException
     */
    public NegaMaxBot() throws InvalidBotException {
        this.setBotName("NegaMax2");
        this.addAuthor("Phillip");
    }

    /**
     * Rien à faire
     */
    @Override
    public void initialize() {
    }

    /**
     * Pas d'apprentissage
     */
    @Override
    public void learn() {
    }

    /**
     * Sélection du coup selon l'algorithme MinMax
     */
    @Override
    public double[] getDecision(Board board) {
        long start = System.currentTimeMillis();
        NegaMaxNode.initialize(NegaMaxBot.MAX_DEPTH );
        double[] res = new NegaMaxNode(board, 0, board.getCurrentPlayer(), Board.otherPlayer(board.getCurrentPlayer()))
                .getDecision();
        long end = System.currentTimeMillis();
        try {
            File myObj = new File("log_negamaxV2.txt");
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(myObj.getAbsoluteFile(), true);
            myWriter.write((end - start)+"\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Rien à faire
     */
    @Override
    public void finish() {
    }
}