
package cofix.common.localization;

import com.gzoltar.core.spectra.Spectra;

import cofix.common.localization.alg.Algorithm;

/**
 * @author anonymous
 * 
 */
public class SBFL {

  public static void sfl(Spectra spectra, Algorithm algorithm) {
    for (int i = 0; i < spectra.getNumberOfComponents(); ++i) {
      double n11;
      double n10;
      double n01;
      double n00 = n01 = n10 = n11 = 0.0D;

      for (int j = 0; j < spectra.getNumberOfTests(); ++j) {
        boolean testResult = spectra.getTestResult(j);

        if (spectra.isCovered(j, i)) {
          if (!(testResult)) n11 += 1.0D;
          else {
            n10 += 1.0D;
          }
        } else if (!(testResult)) n01 += 1.0D;
        else {
          n00 += 1.0D;
        }
      }

      spectra.setSuspiciouness(i, algorithm.compute(n00, n01, n10, n11));
    }
  }
}
