import java.util.*;

public class TokenizerTest {
    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer("pugazholi@test.com;tester@test.com");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}
