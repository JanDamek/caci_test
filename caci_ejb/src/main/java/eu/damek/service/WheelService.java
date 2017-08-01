package eu.damek.service;

import javax.ejb.Stateless;
import java.util.Random;

/**
 * Project: caci_test
 * For:
 * Created by damekjan on 01/08/2017.
 */
@Stateless
public class WheelService {

    /**
     * generate random number of spin wheel
     *
     * @return int of number on pocket -1 = 00
     */
    public int generatePocketNumber() {
        return new Random().nextInt(38) - 1;
    }
}
