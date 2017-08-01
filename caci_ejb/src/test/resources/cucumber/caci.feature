#  Below are some requirements for a game of roulette. (https://en.wikipedia.org/wiki/Roulette)
#  Please create a working game in Java which demonstrates these features.
#  Your design should be guided by the tests you write (TDD). We're looking for strong OO design and a clear implementation.

# Created by damekjan at 31/07/2017
Feature: CACI test

  Scenario: Stage 1
    Given A customer places a bet of £10 on a pocket
    When I spin the roulette wheel and ball lands in a losing pocket
    Then The customer will receive £0 winnings

    Given A customer places a bet of £10 on a pocket
    When I spin the roulette wheel and the ball lands in a winning pocket
    Then The customer will receive £360 winnings

  Scenario: Stage 2
    Given A customer has placed a bet
    When That bet is less than or equal to £0
    Then The application will throw a RouletteGameException with a suitable message

    Given A customer has placed a bet
    When A customer has selected an invalid pocket
    Then The application will throw a RouletteGameException with a suitable message

  Scenario: Stage 3
    Given A customer has placed a bet of £10 on odd or even
    When I spin the roulette wheel and the ball lands in pocket 0
    Then The customer will receive £0 winnings

    Given A customer has placed a bet of £10 on even
    When I spin the roulette wheel and the ball lands on an even pocket
    Then The customer will receive £20 winnings

    Given A customer has placed a bet of £10 on even
    When I spin the roulette wheel and the ball lands on an odd pocket
    Then The customer will receive £0 winnings

    Given A customer has placed a bet of £10 on odd
    When I spin the roulette wheel and the ball lands on an even pocket
    Then The customer will receive £0 winnings

    Given A customer has placed a bet of £10 on odd
    When I spin the roulette wheel and the ball lands on an odd pocket
    Then The customer will receive £20 winnings

  Scenario: Stage 4
    Given A customer has placed a bet of £10 on "00"
    When I spin the roulette wheel and the ball lands on "00"
    Then The customer will receive £360 winnings

    Given A customer has placed a bet of £10 on "00"
    When I spin the roulette wheel and the ball lands on "5"
    Then The customer will receive £0 winnings

    Given A customer has placed a bet of £10 on "0"
    When I spin the roulette wheel and the ball lands on "0"
    Then The customer will receive £360 winnings

    Given A customer has placed a bet of £10 on "0"
    When I spin the roulette wheel and the ball lands on "5"
    Then The customer will receive £0 winnings

    Given A customer has placed a bet of £10 on "00"
    When I spin the roulette wheel and the ball lands on "0"
    Then The customer will receive £0 winnings
