package eu.damek.entity;

import eu.damek.exception.RouletteGameException;
import eu.damek.model.WinRule;

import javax.persistence.Embeddable;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 15/08/2017.
 */
@Embeddable
public class Bet {

    /**
     * store a amount of bet
     */
    private int betAmount;
    /**
     * bet for rule
     */
    private WinRule betAs;

    /**
     * getter for bet
     *
     * @return bet
     */
    public Integer getBet() {
        return betAmount;
    }

    /**
     * setter for bet
     *
     * @param bet bet
     * @throws RouletteGameException invalid set for bet
     */
    public void setBet(Integer bet) throws RouletteGameException {
        if (bet <= 0) {
            throw new RouletteGameException("Invalid BET");
        }
        this.betAmount = bet;
    }

    /**
     * getter for bet rule
     *
     * @return return Class of bet rule
     */
    public WinRule getBetAs() {
        return betAs;
    }

    /**
     * setter for bet rule
     *
     * @param betAs return on Object for bet rule
     */
    public void setBetAs(WinRule betAs) {
        this.betAs = betAs;
    }
}
