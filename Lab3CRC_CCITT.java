import java.util.Scanner;

public class Lab3CRC_CCITT 
{

    // CRC-CCITT (16-bit) generator polynomial
    static final String POLY = "10001000000100001"; // x^16 + x^12 + x^5 + 1

    // XOR operation
    static char xor(char a, char b)
    {
        return (a == b) ? '0' : '1';
    }

    static String divide(char[] dividend, char[] divisor) 
    {

        char[] data = dividend.clone(); // avoid modifying original
        int n = data.length;
        int m = divisor.length;

        for (int i = 0; i <= n - m; i++) 
        {
            if (data[i] == '1') 
            {
                for (int j = 0; j < m; j++) 
                {
                    data[i + j] = xor(data[i + j], divisor[j]);
                }
            }
        }
        // return 16-bit remainder
        return new String(data).substring(n - 16);
    }

    static String encode(String data) 
    {
        // Append 16 zeros
        String paddedData = data + "0".repeat(16);
        String remainder = divide(paddedData.toCharArray(), POLY.toCharArray());
        return data + remainder;
    }

    static boolean decode(String receivedData) 
    {
        String remainder = divide(receivedData.toCharArray(), POLY.toCharArray());
        // If remainder has any 1 → error
        return remainder.contains("1");
    }

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter binary data: ");
        String data = sc.next();

        String encodedData = encode(data);
        System.out.println("Encoded Data: " + encodedData);

        System.out.print("Enter received data: ");
        String received = sc.next();

        if (decode(received))
            System.out.println("Error detected in received data");
        else
            System.out.println("Data is error free");

        sc.close();
    }
}
