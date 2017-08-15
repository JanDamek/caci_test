package eu.damek.service;

import eu.damek.model.WinRule;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 15/08/2017.
 */
public class PocketDoubleZeroWinRule implements WinRule {

    /**
     * multiplier for rule
     */
    private static final int WIN_MULTIPLY = 36;
    /**
     * constant for double zero on wheel
     */
    public static final Integer POCKET_DOUBLE_ZERO = -1;

    /**
     * {@inheritDoc}
     */
    @Override
    public int isWin(Integer pocket) {
        return pocket.equals(POCKET_DOUBLE_ZERO) ? WIN_MULTIPLY : 0;
    }
}
