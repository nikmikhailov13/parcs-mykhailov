import java.math.BigInteger;
import parcs.*;

class Fermat implements AM {

  private static final BigInteger ONE = BigInteger.ONE;

  void run(AMInfo info) {
    BigInteger n = (BigInteger) info.parent.readObject();
    BigInteger res1 = FermatFactor(n);
    BigInteger res2 = n.divide(res1);
    var p = info.createPoint();
    var c = p.createChannel();
    p.execute("Algorithm");
    c.write(n);
    info.parent.write(res1);
    info.parent.write(res2);
  }

  private BigInteger FermatFactor(BigInteger n) {
    var a = sqrt(n);
    var b2 = a.multiply(a).subtract(n);
    while (!isSquare(b2)) {
      a = a.add(ONE);
      b2 = a.multiply(a).subtract(n);
    }
    return a.subtract(sqrt(b2));
  }

  private BigInteger sqrt(BigInteger x) {
    if (x.compareTo(BigInteger.ZERO) < 0) {
      throw new IllegalArgumentException("Negative argument.");
    }
    // square roots of 0 and 1 are trivial and
    // y == 0 will cause a divide-by-zero exception
    if (x.equals(BigInteger.ZERO) || x.equals(BigInteger.ONE)) {
      return x;
    } // end if
    BigInteger two = BigInteger.valueOf(2L);
    BigInteger y;
    // starting with y = x / 2 avoids magnitude issues with x squared
    y = x.divide(two);
    while (y.compareTo(x.divide(y)) > 0) {
      y = ((x.divide(y)).add(y)).divide(two);
    }
    if (x.compareTo(y.multiply(y)) == 0) {
      return y;
    } else {
      return y.add(BigInteger.ONE);
    }
  }

  private boolean isSquare(BigInteger n) {
    var sqr = sqrt(n);
    return sqr.multiply(sqr).equals(n) || (sqr.add(ONE)).multiply(sqr.add(ONE)).equals(n);
  }
}