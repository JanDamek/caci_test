package eu.damek.service;

import eu.damek.dao.CustomerDAO;
import eu.damek.entity.Bet;
import eu.damek.entity.Customer;
import eu.damek.exception.RouletteGameException;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 31/07/2017.
 */
@Stateless
public class GameRouletteService {

    /**
     * injection of wheel service
     */
    @Inject
    private WheelService wheelService;
    /**
     * injection of customerDAO
     */
    @Inject
    private CustomerDAO customerDAO;
    /**
     * number of pocket
     */
    private int pocketNumber;

    /**
     * getter for pocket number
     *
     * @return last pocket number
     */
    public int getPocketNumber() {
        return pocketNumber;
    }

    /**
     * set the number of pocket
     *
     * @param pocketNumber pocket number
     */
    public void setPocketNumber(int pocketNumber) {
        this.pocketNumber = pocketNumber;
    }

    /**
     * set the bet for customer por pocket number
     *
     * @param customer     customer for set bet
     * @param bet          amounts of bet
     * @param betForPocket pocket number for bet
     * @throws RouletteGameException exception for illegal setting
     */
    public void customerBetForNumberOrZero(Customer customer, Integer bet, int betForPocket)
            throws RouletteGameException {
        Bet bet1 = new Bet();
        bet1.setBet(bet);
        bet1.setBetAs(new PocketWinRule(betForPocket));
        customer.getBets().add(bet1);
    }

    /**
     * return a profit for customer after game
     *
     * @param customer for take profit
     * @return return the amount of win or zero for lose
     */
    public Integer customerTakeProfit(Customer customer) {
        return customer.takeProfit(getPocketNumber());
    }


    /**
     * set the bet for odd numbers
     *
     * @param customer for bet
     * @param bet      amount of bet
     * @throws RouletteGameException when the bet is illegal
     */
    public void customerBetForOdd(Customer customer, int bet) throws RouletteGameException {
        Bet bet1 = new Bet();
        bet1.setBet(bet);
        bet1.setBetAs(new OddWinRule());
        customer.getBets().add(bet1);
    }

    /**
     * set the bet for even numbers
     *
     * @param customer for bet
     * @param bet      amount of bet
     * @throws RouletteGameException when the bet is illegal
     */
    public void customerBetForEven(Customer customer, int bet) throws RouletteGameException {
        Bet bet1 = new Bet();
        bet1.setBet(bet);
        bet1.setBetAs(new EvenWinRule());
        customer.getBets().add(bet1);
    }

    /**
     * set the bet for 00
     *
     * @param customer for bet
     * @param bet      amounts of bet
     * @throws RouletteGameException if the bet is invalid
     */
    public void customerBetForDoubleZero(Customer customer, int bet) throws RouletteGameException {
        Bet bet1 = new Bet();
        bet1.setBet(bet);
        bet1.setBetAs(new PocketDoubleZeroWinRule());
        customer.getBets().add(bet1);
    }

    /**
     * spin the wheel and set generated pocket number
     */
    public void spinWheel() {
        setPocketNumber(wheelService.generatePocketNumber());
    }

    /**
     * find the customer by name or create one
     *
     * @param customer name
     * @return class {@link Customer}
     */
    public Customer findCustomerByName(String customer) {
        return customerDAO.getCutomerByNameOrCrearte(customer);
    }

    /**
     * store the {@link Customer} entity to DB
     *
     * @param c {@link Customer}
     */
    public void storeCustomer(Customer c) {
        customerDAO.merge(c);
    }
}
