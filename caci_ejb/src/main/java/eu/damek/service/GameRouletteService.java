package eu.damek.service;

import eu.damek.dao.CustomerDAO;
import eu.damek.entity.Customer;
import eu.damek.exception.RouletteGameException;
import eu.damek.model.BetType;

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
     * multiply for win by number
     */
    private static final int WIN_BY_NUMBER = 36;
    /**
     * multiply for win by zero
     */
    private static final int WIN_BY_ZERO = 36;
    /**
     * multiply for win by double zero
     */
    private static final int WIN_BY_DOUBLE_ZERO = 36;
    /**
     * multiply for win by odd
     */
    private static final int WIN_BY_ODD = 2;
    /**
     * multiply for win by even
     */
    private static final int WIN_BY_EVEN = 2;
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
        customer.setBet(bet);
        customer.setPocketNumber(betForPocket);
        customer.setBetType(BetType.BET_FOR_NUMBER);
    }

    /**
     * return a profit for customer after game
     *
     * @param customer for take profit
     * @return return the amount of win or zero for lose
     */
    public Integer customerTakeProfit(Customer customer) {
        switch (customer.getBetType()) {
            case BET_FOR_NUMBER:
                return calcWinByNumber(customer);
            case BET_FOR_ODD:
                return calcWinByOdd(customer);
            case BET_FOR_EVEN:
                return calcWinByEven(customer);
            case BET_FOR_00:
                return calcWinByDoubleZero(customer);
            default:
                return 0;
        }
    }

    /**
     * calculation of amounth of win by double zero
     *
     * @param customer customer to calc
     * @return amount of win
     */
    private Integer calcWinByDoubleZero(Customer customer) {
        return pocketNumber == -1 ? customer.getBet() * WIN_BY_DOUBLE_ZERO : 0;
    }

    /**
     * calculation of amounth of win by even
     *
     * @param customer customer to calc
     * @return amount of win
     */
    private Integer calcWinByEven(Customer customer) {
        return pocketNumber > 0 && pocketNumber % 2 == 0 ? customer.getBet() * WIN_BY_EVEN : 0;
    }

    /**
     * calculation of amounth of win by odd
     *
     * @param customer customer to calc
     * @return amount of win
     */
    private Integer calcWinByOdd(Customer customer) {
        return pocketNumber > 0 && pocketNumber % 2 == 1 ? customer.getBet() * WIN_BY_ODD : 0;
    }

    /**
     * calculation of amounth of win by number
     *
     * @param customer customer to calc
     * @return amount of win
     */
    private Integer calcWinByNumber(Customer customer) {
        if (pocketNumber == 0 && customer.getPocketNumber() == 0) {
            return customer.getBet() * WIN_BY_ZERO;
        } else if (customer.getPocketNumber().equals(pocketNumber)) {
            return customer.getBet() * WIN_BY_NUMBER;
        } else {
            return 0;
        }
    }

    /**
     * set the bet for odd numbers
     *
     * @param customer for bet
     * @param bet      amount of bet
     * @throws RouletteGameException when the bet is illegal
     */
    public void customerBetForOdd(Customer customer, int bet) throws RouletteGameException {
        customer.setBet(bet);
        customer.setBetType(BetType.BET_FOR_ODD);
    }

    /**
     * set the bet for even numbers
     *
     * @param customer for bet
     * @param bet      amount of bet
     * @throws RouletteGameException when the bet is illegal
     */
    public void customerBetForEven(Customer customer, int bet) throws RouletteGameException {
        customer.setBet(bet);
        customer.setBetType(BetType.BET_FOR_EVEN);
    }

    /**
     * set the bet for 00
     *
     * @param customer for bet
     * @param bet      amounts of bet
     * @throws RouletteGameException if the bet is invalid
     */
    public void customerBetForDoubleZero(Customer customer, int bet) throws RouletteGameException {
        customer.setBet(bet);
        customer.setBetType(BetType.BET_FOR_00);
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
