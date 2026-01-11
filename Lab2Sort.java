import java.util.Scanner;

class Lab2Sort {

    static class Frame {
        int fnum;
        String content;

        Frame(int fnum, String content) {
            this.fnum = fnum;
            this.content = content;
        }
    }

    // Bubble sort to sort frames by frame number
    static void sortFrames(Frame[] F, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (F[j].fnum > F[j + 1].fnum) {
                    Frame temp = F[j];
                    F[j] = F[j + 1];
                    F[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int n = sc.nextInt();

        Frame[] F = new Frame[n];

        System.out.println("Enter frame number and content:");
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            String cont = sc.next();
            F[i] = new Frame(num, cont);
        }

        System.out.println("\nBefore Sorting:");
        for (int i = 0; i < n; i++) {
            System.out.print(F[i].content + " ");
        }

        sortFrames(F, n);

        System.out.println("\n\nAfter Sorting:");
        for (int i = 0; i < n; i++) {
            System.out.print(F[i].content + " ");
        }

        sc.close();
    }
}
