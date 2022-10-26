public class Main {
    public static void main(String[] args) {
        Decoder decoder= new Decoder();

        String expectedOutput = "244244244";

        decoder.input((byte) 1);
        decoder.input((byte) 2);
        decoder.input((byte) 1);
        decoder.input((byte) 0);

        decoder.reset();

        decoder.input((byte) 2);
        decoder.input((byte) 4);
        decoder.input((byte) 4);
        decoder.input((byte) 0);
        decoder.input((byte) 0);
        decoder.input((byte) 1);
        decoder.input((byte) 1);
        decoder.input((byte) 2);

        System.out.println(expectedOutput);
        System.out.println(decoder.output());

    }
}
