import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

// RSA Algorithm Class
class RSAAlgorithm {

    BigInteger e, d, n;

    // Key generation
    void generateKeys(int bitLength) {

        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, r);
        BigInteger q = BigInteger.probablePrime(bitLength, r);

        n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE)
                          .multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitLength / 2, r);
        while (!phi.gcd(e).equals(BigInteger.ONE) || e.compareTo(phi) >= 0) {
            e = BigInteger.probablePrime(bitLength / 2, r);
        }

        d = e.modInverse(phi);
    }

    // Encryption: C = M^e mod n
    BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decryption: M = C^d mod n
    BigInteger decrypt(BigInteger cipher) {
        return cipher.modPow(d, n);
    }
}

// Main Class
public class Lab7RSA {

    public static void main(String[] args) {

        RSAAlgorithm rsa = new RSAAlgorithm();
        rsa.generateKeys(512);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String msg = sc.nextLine();

        BigInteger message =
                new BigInteger(1, msg.getBytes());

        BigInteger cipher = rsa.encrypt(message);
        System.out.println("Encrypted message: " + cipher);

        BigInteger decrypted = rsa.decrypt(cipher);
        System.out.println("Decrypted message: " +
                new String(decrypted.toByteArray()));

        sc.close();
    }
}
