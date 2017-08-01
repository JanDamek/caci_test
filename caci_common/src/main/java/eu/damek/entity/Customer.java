package eu.damek.entity;

import eu.damek.exception.RouletteGameException;
import eu.damek.model.BetType;

import javax.persistence.*;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 31/07/2017.
 */
@NamedQueries({
        @NamedQuery(
                name = "Customer.findByName",
                query = "SELECT c FROM Customer c WHERE c.name = :name")
})
@Entity
public class Customer {

    /**
     * name of customer
     */
    @Id
    private String name;
    /**
     * amounts of bet
     */
    private Integer bet;
    /**
     * cash of customer
     */
    private Integer cash;
    /**
     * type of bet
     */
    @Enumerated(EnumType.ORDINAL)
    private BetType betType;
    /**
     * set the pocket number for bet
     */
    private Integer pocketNumber;

    /**
     * getter for name of customer
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * setter for customer name
     *
     * @param name of customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for amount of cash
     *
     * @return Integer
     */
    public Integer getCash() {
        if (cash == null) {
            cash = 0;
        }
        return cash;
    }

    /**
     * setter for customer cash
     *
     * @param cash Integer of cash amount
     */
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    /**
     * getter for bet
     *
     * @return bet
     */
    public Integer getBet() {
        return bet;
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
        this.bet = bet;
    }

    /**
     * getter for bet type
     *
     * @return bet type
     */
    public BetType getBetType() {
        return betType;
    }

    /**
     * setter for bet type
     *
     * @param betType bet type
     */
    public void setBetType(BetType betType) {
        this.betType = betType;
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
}
