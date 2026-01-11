import java.util.Scanner;

public class TokenBucket {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bucket capacity (tokens): ");
        int bucketCapacity = sc.nextInt();

        System.out.print("Enter token generation rate (tokens per second): ");
        int tokenRate = sc.nextInt();

        System.out.print("Enter number of packets: ");
        int numPackets = sc.nextInt();

        int[] packetSize = new int[numPackets];
        System.out.println("Enter packet sizes:");
        for (int i = 0; i < numPackets; i++) {
            packetSize[i] = sc.nextInt();
        }

        int tokens = 0;

        System.out.println("\nPacket\tTokens Available\tTokens Used\tTokens Remaining\tStatus");

        for (int i = 0; i < numPackets; i++) {

            // Generate tokens
            tokens = Math.min(tokens + tokenRate, bucketCapacity);
            int availableTokens = tokens;

            if (packetSize[i] <= tokens) {
                tokens -= packetSize[i];
                System.out.println(packetSize[i] + "\t\t" +
                        availableTokens + "\t\t\t" +
                        packetSize[i] + "\t\t" +
                        tokens + "\t\t\tAccepted");
            } else {
                System.out.println(packetSize[i] + "\t\t" +
                        availableTokens + "\t\t\t0\t\t" +
                        tokens + "\t\t\tDropped");
            }
        }

        sc.close();
    }
}
