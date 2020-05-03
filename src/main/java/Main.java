import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Scanner;
import parcs.*;

public class Main implements AM {

  public static void main(String[] args) {
    var curtask = new task();
    curtask.addJarFile("Fermat.jar");

    var fermat = new Fermat();
    fermat.run(new AMInfo(curtask, (channel) null));
    curtask.end();
  }

  public void run(AMInfo info) {
    BigInteger num1;
    BigInteger num2;
    BigInteger n;
    try {
      try (var sc = new Scanner(new File(info.curtask.findFile("Fermat.data")))) {
        n = sc.nextBigInteger();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("Input string: " + n);
    var p = info.createPoint();
    var c = p.createChannel();
    p.execute("Algorithm");
    c.write(n);
    System.out.println("Waiting for result...");
    num1 = (BigInteger) c.readObject();
    num2 = (BigInteger) c.readObject();
    System.out.println("Result found.");
    System.out.println(num1);
    System.out.println(num2);
    try (var out = new PrintWriter(new FileWriter(info.curtask.addPath("Fermat.res")))) {
      out.println(num1);
      out.println(num2);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
