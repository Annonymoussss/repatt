
package cofix.common.localization.alg;

/**
 * @author anonymous
 * 
 */
public class Ochiai extends Algorithm {

  @Override
  public double compute(double n00, double n01, double n10, double n11) {
    if ((n11 + n10 == 0.0D) || (n11 + n01 == 0.0D)) {
      return 0.0D;
    }
    return (n11 / Math.sqrt((n11 + n01) * (n11 + n10)));
  }
}
