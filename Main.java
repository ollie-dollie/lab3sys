import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> nums = new ArrayList<>();
        for (String line : Files.readAllLines(Paths.get("numbers.txt"))) {
            for (String token : line.trim().split("\\s+")) {
                if (!token.isEmpty()) {
                    nums.add(Integer.parseInt(token));
                }
            }
        }

        int min37 = Integer.MAX_VALUE;
        int max73 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x % 37 == 0 && x < min37) min37 = x;
            if (x % 73 == 0 && x > max73) max73 = x;
        }

        System.out.println("Минимальное кратное 37: " + min37);
        System.out.println("Максимальное кратное 73: " + max73);

        int left  = Math.min(min37, max73);
        int right = Math.max(min37, max73);

        int count = 0;
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < nums.size() - 1; i++) {
            int a = nums.get(i);
            int b = nums.get(i + 1);

            boolean inA = (a >= left && a <= right);
            boolean inB = (b >= left && b <= right);

            if (inA ^ inB) {
                count++;
                int sum = a + b;
                if (sum < minSum) minSum = sum;
            }
        }

        System.out.println("Количество пар: " + count);
        System.out.println("Минимальная сумма: " + minSum);
    }
}