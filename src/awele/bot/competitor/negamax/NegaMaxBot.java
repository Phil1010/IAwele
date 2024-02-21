package awele.bot.competitor.negamax;

import awele.bot.CompetitorBot;
import awele.bot.DemoBot;
import awele.core.Board;
import awele.core.InvalidBotException;

/**
 * @author Alexandre Blansché
 * Bot qui prend ses décisions selon le MinMax
 */
public class NegaMaxBot extends CompetitorBot
{
    /** Profondeur maximale */
    private static final int MAX_DEPTH = 5;
	
    /**
     * @throws InvalidBotException
     */
    public NegaMaxBot() throws InvalidBotException
    {
        this.setBotName ("NegaMax");
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
        NegaMaxNode.initialize(board, NegaMaxBot.MAX_DEPTH);
        return new NegaMaxNode(board).getDecision();
    }

    /**
     * Rien à faire
     */
    @Override
    public void finish ()
    {
    }
}
