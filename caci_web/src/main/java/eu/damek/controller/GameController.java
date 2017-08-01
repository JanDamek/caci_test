package eu.damek.controller;

import eu.damek.entity.Customer;
import eu.damek.exception.RouletteGameException;
import eu.damek.service.GameRouletteService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 01/08/2017.
 */
@ManagedBean
@SessionScoped
@Stateless
public class GameController {

    private static final String ODD = "Odd";
    private static final String EVEN = "Even";
    private static final String DOUBLE_ZERO = "00";
    @Inject
    private GameRouletteService gameRouletteService;

    private String customer;
    private String bet;
    private List<String> bets;
    private List<String> pockets;
    private String pocket;
    private String lastWin;

    @PostConstruct
    private void init() {
        bets = Arrays.asList("10", "20", "30");
        pockets = Arrays.asList("0", DOUBLE_ZERO, "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
                "16", "17", "18", "19", "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29", "30", "31", "32", "33",
                "34", "35", "36", ODD, EVEN);
        lastWin = "no spin";
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setBetAndSpinWheel() {
        Customer c = gameRouletteService.findCustomerByName(customer);
        gameRouletteService.spinWheel();
        try {
            if (pocket.equalsIgnoreCase(DOUBLE_ZERO)) {
                gameRouletteService.customerBetForDoubleZero(c, Integer.valueOf(bet));
            } else if (pocket.equalsIgnoreCase(ODD)) {
                gameRouletteService.customerBetForOdd(c, Integer.valueOf(bet));
            } else if (pocket.equalsIgnoreCase(EVEN)) {
                gameRouletteService.customerBetForEven(c, Integer.valueOf(bet));
            } else {
                gameRouletteService.customerBetForNumberOrZero(c, Integer.valueOf(bet), Integer.valueOf(pocket));
            }
            Integer takeProfit = gameRouletteService.customerTakeProfit(c);
            c.setCash(c.getCash() - Integer.valueOf(bet) + takeProfit);
            gameRouletteService.storeCustomer(c);
            lastWin = takeProfit.toString();
        } catch (RouletteGameException e) {
            lastWin = e.getMessage();
        }
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public List<String> getBets() {
        return bets;
    }

    public List<String> getPockets() {
        return pockets;
    }

    public String getPocket() {
        return pocket;
    }

    public void setPocket(String pocket) {
        this.pocket = pocket;
    }

    public String getLastPocketNumber() {
        return String.valueOf(gameRouletteService.getPocketNumber());
    }

    public String getLastWin() {
        return lastWin;
    }

    public String getCash() {
        if (customer == null || customer.isEmpty()) {
            return "not set customer";
        }
        Customer c = gameRouletteService.findCustomerByName(customer);
        return c.getCash().toString();
    }
}
