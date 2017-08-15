package eu.damek.service;

import eu.damek.model.WinRule;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 15/08/2017.
 */
public class EvenWinRule implements WinRule {

    /**
     * multiplier for rule
     */
    private static final int WIN_MULTIPLY = 2;

    /**
     * {@inheritDoc}
     */
    @Override
    public int isWin(Integer pocket) {
        return pocket > 0 && pocket % 2 == 0 ? WIN_MULTIPLY : 0;
    }

}
