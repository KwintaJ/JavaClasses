import java.util.Objects;

public class Main {
    static Decoder decoder= new Decoder();

    public static void main(String[] args) {


        String expectedOutput0 = "";
        String Test0 = "#  ";
        test(expectedOutput0, Test0);

        String expectedOutput1 = "482148";
        String Test1 = "4 8 2 1 4 8 0 0 0 0 1 #  ";
        test(expectedOutput1, Test1);

        String expectedOutput2 = "";
        String Test2 = "4 8 2 1 4 8 0 0 0 0 0 #  ";
        test(expectedOutput2, Test2);

        String expectedOutput3 = "12345";
        String Test3 = "1 2 3 10 11 12 4 13 5 0 0 0 0 1 #  ";
        test(expectedOutput3, Test3);

        String expectedOutput4 = "22222222222222";
        String Test4 = "2 0 0 0 1 4 #  ";
        test(expectedOutput4, Test4);

    }

    public static void test(String expectedOutput, String inputData) {
        StringBuilder inp = new StringBuilder();
        for (int i = 0; i < inputData.length() - 1; i++) {
            String a = inputData.substring(i, i+1);
            if(a.equals(" ")) {
                if(Objects.equals(inp.toString(), "r")) {
                    decoder.reset();
                }
                else if(Objects.equals(inp.toString(), "#")) {
                    System.out.println("output:   " + decoder.output());
                }
                else
                    decoder.input(Byte.parseByte(inp.toString()));

                inp = new StringBuilder();
            }
            else
                inp.append(inputData.substring(i, i + 1));
        }

        System.out.println("expected: " + expectedOutput);
        System.out.println("-----------------------------");
        decoder.reset();
    }
}
