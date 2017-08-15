package eu.damek.service;

import eu.damek.exception.RouletteGameException;
import eu.damek.model.WinRule;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 15/08/2017.
 */
public class PocketWinRule implements WinRule {

    /**
     * multiplier for rule
     */
    private static final int WIN_MULTIPLY = 36;
    /**
     * bet for pocket number
     */
    private Integer pocketNumber;

    /**
     * constructor for pocket win rule
     *
     * @param pocket number for win
     * @throws RouletteGameException if the pocket number is invalid
     */
    PocketWinRule(int pocket) throws RouletteGameException {
        setPocketNumber(pocket);
    }

    /**
     * getter for pocket number
     *
     * @return pocket number on bet
     */
    public Integer getPocketNumber() {
        return pocketNumber;
    }

    /**
     * setter pocket number
     *
     * @param pocketNumber pocket number
     * @throws RouletteGameException invalid set for pocket number
     */
    public void setPocketNumber(Integer pocketNumber) throws RouletteGameException {
        if (pocketNumber < 0 || pocketNumber > 36) {
            throw new RouletteGameException("Invalid pocket number");
        }
        this.pocketNumber = pocketNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int isWin(Integer pocket) {
        return this.pocketNumber.equals(pocket) ? WIN_MULTIPLY : 0;
    }
}
