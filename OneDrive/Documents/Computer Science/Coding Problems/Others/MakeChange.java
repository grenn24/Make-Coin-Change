import java.util.List;
import java.util.Scanner;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MakeChange {
    public static List<List<Integer>> map(Integer x, List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            list.add(x);
        }
        return lists;
    }
    
    public static List<List<Integer>> makeChange(int sum, List<Integer> coins) {
        if (sum == 0) {
            List<List<Integer>> lst2 = new ArrayList<>();
            lst2.add(new ArrayList<>()); //Represents a set of empty set {∅} --> in source, it is equivalent to list(null) null is an empty list (in source at least)
            return lst2; //list of a empty list
        } else if (sum < 0 || coins.isEmpty()) {
            return new ArrayList<>(); // Return empty list when no solution is possible
        } else {
            // Exclude current coin
            List<List<Integer>> excludeCurrentCoin = makeChange(sum, coins.subList(1, coins.size()));
            
            // Add current coin to all solutions that included it
            List<List<Integer>> includeCurrentCoin = map(coins.get(0), makeChange(sum - coins.get(0), coins));
    
            // Combine both results (exclude and include)
            excludeCurrentCoin.addAll(includeCurrentCoin);
    
            return excludeCurrentCoin;
        }
    }    
    

    public static void main(String[] args) {
        List<Integer> coins  = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Add a coin (enter -1 to stop): ");
            int coin = scanner.nextInt();
            if (coin == -1) {
                break;
            }
            if (!coins.contains(coin)) {
                coins.add(coin);
            }
        }
        System.out.print("Enter total amount: ");
        int sum = scanner.nextInt();
        scanner.close();
        System.out.println(makeChange(sum, coins));
    }}