import java.util.*;

class Lab2FrameSorting {

    static class Packet {
        int seq;
        String data;

        Packet(int seq, String data) {
            this.seq = seq;
            this.data = data;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String message = sc.nextLine();

        System.out.print("Enter packet size: ");
        int size = sc.nextInt();

        // Divide message into packets
        List<Packet> packets = new ArrayList<>();
        int seq = 0;

        for (int i = 0; i < message.length(); i += size) {
            String part = message.substring(i,
                    Math.min(i + size, message.length()));
            packets.add(new Packet(seq++, part));
        }

        // Shuffle packets to simulate out-of-order reception
        Collections.shuffle(packets);

        // Display received packets (shuffled)
        System.out.println("\nPackets received (shuffled):");
        for (Packet p : packets) {
            System.out.println("Seq " + p.seq + " : " + p.data);
        }

        // Sort packets by sequence number
        packets.sort(Comparator.comparingInt(p -> p.seq));

        // Display sorted packets
        System.out.println("\nPackets after sorting:");
        for (Packet p : packets) {
            System.out.println("Seq " + p.seq + " : " + p.data);
        }

        // Reassemble message
        StringBuilder reassembled = new StringBuilder();
        for (Packet p : packets) {
            reassembled.append(p.data);
        }

        System.out.println("\nReassembled Message:");
        System.out.println(reassembled.toString());

        sc.close();
    }
}
