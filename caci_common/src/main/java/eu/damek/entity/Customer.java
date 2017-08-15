package eu.damek.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
     * cash of customer
     */
    private Integer cash;
    /**
     * list of bets
     */
    @ElementCollection
    private List<Bet> bets;

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
     * getter for list of all bets
     *
     * @return list of all Bet
     */
    public List<Bet> getBets() {
        if (bets == null) {
            bets = new ArrayList<>();
        }
        return bets;
    }

    /**
     * setter for list of bets
     *
     * @param bets list of bets
     */
    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    /**
     * method for test all bets and return all profit from bets
     *
     * @param pocketNumber pocket number on wheel
     * @return all wined cash
     */
    @Transient
    public Integer takeProfit(int pocketNumber) {
        final Integer[] allProfit = {0};
        bets.forEach(b -> {
            allProfit[0] += b.getBetAs().isWin(pocketNumber) * b.getBet();
        });
        bets.clear();
        return allProfit[0];
    }
}
