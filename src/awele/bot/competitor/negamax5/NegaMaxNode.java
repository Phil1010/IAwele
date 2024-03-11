package awele.bot.competitor.negamax5;

import awele.core.Board;
import awele.core.InvalidBotException;

/**
 * Noeud d'un arbre MinMax
 */
public class NegaMaxNode {
    private static int maxDepth;

    private final double depth;
    private double evaluation;
    private final double[] decision;

    /**
     * Constructeur
     *
     * @param board         L'état de la grille de jeu
     * @param depth         La profondeur du noeud
     * @param myTour        Le tour du joueur
     * @param opponentTour  Le tour de l'adversaire
     * @param a             Valeur alpha
     * @param b             Valeur beta
     */
    public NegaMaxNode(Board board, double depth, int myTour, int opponentTour, double a, double b) {
        this.depth = depth;
        this.decision = new double[Board.NB_HOLES];
        this.evaluation = Double.NEGATIVE_INFINITY;
        Board copy;
        double[] decisionTemp = new double[Board.NB_HOLES];

        for (int i = 0; i < Board.NB_HOLES; i++) {
            if (board.getPlayerHoles()[i] != 0) {
                decisionTemp[i] = (i + 1);
                try {
                    copy = board.playMoveSimulationBoard(myTour, decisionTemp);
                } catch (InvalidBotException e) {
                    throw new RuntimeException(e);
                }
                if (copy.getNbSeeds() <= 6 || depth >= NegaMaxNode.maxDepth) {
                    this.decision[i] = eval(copy, myTour, opponentTour);
                } else {
                    NegaMaxNode child = new NegaMaxNode(copy, depth + 1, opponentTour, myTour, -b, -a);
                    this.decision[i] = -child.getEvaluation();
                }

                if (this.decision[i] > this.evaluation) {
                    this.evaluation = this.decision[i];
                }

                if (depth > 0) {
                    a = Math.max(a, this.decision[i]);
                    if (a >= b) {
                        break;
                    }
                }
            }
        }
    }

    /**
     * Initialise la profondeur maximale
     *
     * @param maxDepth Profondeur maximale
     */
    protected static void initialize(int maxDepth) {
        NegaMaxNode.maxDepth = maxDepth;
    }

    /**
     * Évalue l'état actuel de la grille de jeu
     *
     * @param board         L'état de la grille de jeu
     * @param myTour        Le tour du joueur
     * @param opponentTour  Le tour de l'adversaire
     * @return              L'évaluation de la situation actuelle
     */
    private int eval(Board board, int myTour, int opponentTour) {
        int total = 0;
        int[] seedsPlayer = board.getPlayerHoles(), seedsOpponent = board.getOpponentHoles();

        for (int i = 0; i < Board.NB_HOLES; i++) {
            int seedP = seedsPlayer[i];
            int seedO = seedsOpponent[i];
            if (seedP > 12)
                total += 2;
            else if (seedP == 0)
                total -= 5;
            else if (seedP < 3)
                total -= 3;
            if (seedO > 12)
                total -= 2;
            else if (seedO == 0)
                total += 5;
            else if (seedO < 3)
                total += 3;
        }
        return (2 * (board.getScore(myTour) - board.getScore(opponentTour))) - total;
    }

    /**
     * Renvoie l'évaluation du noeud
     *
     * @return  L'évaluation du noeud
     */
    double getEvaluation() {
        return this.evaluation;
    }

    /**
     * Renvoie l'évaluation de chaque coup possible pour le noeud
     *
     * @return  L'évaluation de chaque coup possible
     */
    double[] getDecision() {
        return this.decision;
    }

    /**
     * Renvoie la profondeur du noeud
     *
     * @return  La profondeur du noeud
     */
    public double getDepth() {
        return depth;
    }
}
