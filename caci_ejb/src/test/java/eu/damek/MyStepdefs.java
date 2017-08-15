package eu.damek;

import cucumber.api.java8.En;
import eu.damek.entity.Customer;
import eu.damek.exception.RouletteGameException;
import eu.damek.service.GameRouletteService;
import org.junit.Assert;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 31/07/2017.
 */
public class MyStepdefs implements En {

    private boolean exceptionRouletteGame;
    private GameRouletteService gameRouletteService = new GameRouletteService();
    private Customer customer = new Customer();

    public MyStepdefs() {
        Given("^A customer places a bet of £(\\d+) on a pocket$", (Integer bet) -> {
            try {
                gameRouletteService.customerBetForNumberOrZero(customer, bet, 13);
            } catch (RouletteGameException e) {
                e.printStackTrace();
            }
        });

        When("^I spin the roulette wheel and ball lands in a losing pocket$", () -> {
            gameRouletteService.setPocketNumber(0);
        });
        Then("^The customer will receive £(\\d+) winnings$", (Integer receive) -> {
            Assert.assertEquals(receive, gameRouletteService.customerTakeProfit(customer), 0.01);
        });

        When("^I spin the roulette wheel and the ball lands in a winning pocket$", () -> {
            gameRouletteService.setPocketNumber(13);
        });
        Given("^A customer has placed a bet$", () -> {
        });
        When("^That bet is less than or equal to £(\\d+)$", (Integer bet) -> {
            exceptionRouletteGame = false;
            try {
                gameRouletteService.customerBetForNumberOrZero(customer, bet, 13);
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        Then("^The application will throw a RouletteGameException with a suitable message$", () -> {
            Assert.assertEquals(exceptionRouletteGame, true);
        });

        When("^A customer has selected an invalid pocket$", () -> {
            exceptionRouletteGame = false;
            try {
                gameRouletteService.customerBetForNumberOrZero(customer, 10, 77);
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        Given("^A customer has placed a bet of £(\\d+) on odd or even$", (Integer bet) -> {
            exceptionRouletteGame = false;
            try {
                gameRouletteService.customerBetForOdd(customer, bet);
                gameRouletteService.customerBetForEven(customer, bet);
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        When("^I spin the roulette wheel and the ball lands in pocket (\\d+)$", (Integer lands) -> {
            gameRouletteService.setPocketNumber(lands);
        });
        Given("^A customer has placed a bet of £(\\d+) on even$", (Integer bet) -> {
            exceptionRouletteGame = false;
            try {
                gameRouletteService.customerBetForEven(customer, bet);
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        When("^I spin the roulette wheel and the ball lands on an even pocket$", () -> {
            gameRouletteService.setPocketNumber(2);
        });
        When("^I spin the roulette wheel and the ball lands on an odd pocket$", () -> {
            gameRouletteService.setPocketNumber(3);
        });
        Given("^A customer has placed a bet of £(\\d+) on odd$", (Integer bet) -> {
            exceptionRouletteGame = false;
            try {
                gameRouletteService.customerBetForOdd(customer, bet);
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        Given("^A customer has placed a bet of £(\\d+) on \"([^\"]*)\"$", (Integer bet, String pocket) -> {
            exceptionRouletteGame = false;
            try {
                if (pocket.equalsIgnoreCase("00")) {
                    gameRouletteService.customerBetForDoubleZero(customer, bet);
                } else {
                    gameRouletteService.customerBetForNumberOrZero(customer, bet, Integer.valueOf(pocket));
                }
            } catch (RouletteGameException e) {
                exceptionRouletteGame = true;
            }
        });
        When("^I spin the roulette wheel and the ball lands on \"([^\"]*)\"$", (String pocket) -> {
            if (pocket.equalsIgnoreCase("00")) {
                gameRouletteService.setPocketNumber(-1);
            } else {
                gameRouletteService.setPocketNumber(Integer.valueOf(pocket));
            }
        });
    }
}
