/**
 * @author YC 03/02/2020
 */

import java.util.HashMap;
import java.util.TreeMap;

public class QuickCheck {
    public static void twoSum(int[] nums) {
        HashMap<Integer, Integer> hM = new HashMap<>();
        TreeMap<Integer, Integer> tM = new TreeMap<>();

        for (int i = 0; i < nums.length; i++) {
            hM.put(nums[i], i);
            tM.put(nums[i], i);
        }

        System.out.println(hM.entrySet());
        System.out.println(tM.entrySet());
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 1};
        twoSum(nums);
    }
}
