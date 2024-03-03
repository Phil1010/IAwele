package awele.bot.competitor.negaMax3;

import awele.bot.CompetitorBot;
import awele.core.Board;
import awele.core.InvalidBotException;

/**
 * @author Alexandre Blansché
 * Bot qui prend ses décisions selon le MinMax
 */
public class NegaMaxBot extends CompetitorBot {
    /**
     * Profondeur maximale
     */
    private static final int MAX_DEPTH = 9;

    /**
     * @throws InvalidBotException
     */
    public NegaMaxBot() throws InvalidBotException {
        this.setBotName("NegaMax3 Profondeur = " + MAX_DEPTH);
        this.addAuthor("Phillip");
    }

    /**
     * Fonction d'initalisation du bot
     * Cette fonction est appelée avant chaque affrontement
     */
    @Override
    public void initialize() {
        NegaMaxNode.initialize(NegaMaxBot.MAX_DEPTH);
    }

    /**
     * Pas d'apprentissage
     */
    @Override
    public void learn() { }

    /**
     * Sélection du coup selon l'algorithme MinMax
     */
    @Override
    public double[] getDecision(Board board) {
        return new NegaMaxNode(board, 0, board.getCurrentPlayer(), Board.otherPlayer(board.getCurrentPlayer()), -9999, 9999).getDecision();
    }

    /**
     * Rien à faire
     */
    @Override
    public void finish() { }
}