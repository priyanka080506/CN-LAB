import java.util.Scanner;

public class LeakyBucket 
{

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bucket capacity: ");
        int bucketCapacity = sc.nextInt();

        System.out.print("Enter output rate (packets per second): ");
        int outputRate = sc.nextInt();

        System.out.print("Enter number of packets: ");
        int numPackets = sc.nextInt();

        int[] packetSize = new int[numPackets];
        System.out.println("Enter packet sizes:");
        for (int i = 0; i < numPackets; i++) 
        {
            packetSize[i] = sc.nextInt();
        }

        int currentBucket = 0;

        System.out.println("\nPacket\tBucket\tSent\tRemaining\tStatus");

        for (int i = 0; i < numPackets; i++) 
        {

            // Check if packet can be added
            if (currentBucket + packetSize[i] <= bucketCapacity) 
            {
                currentBucket += packetSize[i];
                System.out.print(packetSize[i] + "\t" + currentBucket + "\t");
                System.out.print(Math.min(outputRate, currentBucket) + "\t");
                currentBucket -= Math.min(outputRate, currentBucket);
                System.out.println(currentBucket + "\t\tAccepted");
            } 
            else 
            {
                System.out.print(packetSize[i] + "\t" + currentBucket + "\t");
                System.out.print(Math.min(outputRate, currentBucket) + "\t");
                currentBucket -= Math.min(outputRate, currentBucket);
                System.out.println(currentBucket + "\t\tDropped");
            }
        }

        sc.close();
    }
}
