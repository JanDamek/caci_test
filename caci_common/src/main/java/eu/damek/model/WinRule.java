package eu.damek.model;

import java.io.Serializable;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 15/08/2017.
 */
public interface WinRule extends Serializable {

    /**
     * return a multiply for rule
     *
     * @param pocket on the wheel
     * @return multiplier for rule
     */
    int isWin(Integer pocket);

}
