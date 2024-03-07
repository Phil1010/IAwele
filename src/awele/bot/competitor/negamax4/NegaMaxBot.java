package awele.bot.competitor.negamax4;

import awele.bot.CompetitorBot;
import awele.core.Board;
import awele.core.InvalidBotException;

/**
 * @author Alexandre Blansché
 * Bot qui prend ses décisions selon le MinMax
 */
public class NegaMaxBot extends CompetitorBot
{
    /** Profondeur maximale */
    private static final int MAX_DEPTH = 9; // max = 9
	
    /**
     * @throws InvalidBotException
     */
    public NegaMaxBot() throws InvalidBotException
    {
        this.setBotName ("NegaMax élagage alpha beta fail-hard");
        this.addAuthor ("Nicolas ROBERT");
    }

    /**
     * Rien à faire
     */
    @Override
    public void initialize ()
    {
    }

    /**
     * Pas d'apprentissage
     */
    @Override
    public void learn ()
    {
    }

    /**
     * Sélection du coup selon l'algorithme MinMax
     */
    @Override
    public double [] getDecision (Board board)
    {
        NegaMaxNode.initialize (board, NegaMaxBot.MAX_DEPTH);
        return new MaxNode(board).getDecision ();
    }

    /**
     * Rien à faire
     */
    @Override
    public void finish ()
    {
    }
}
