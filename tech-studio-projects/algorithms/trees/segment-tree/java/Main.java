import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(3);
        nums.add(5);
        nums.add(7);
        nums.add(9);
        
        SegmentTree segmentTree = new SegmentTree(nums);
        
        System.out.println(segmentTree.query(1, 3)); // Output: 15
        
        segmentTree.update(2, 6);
        
        System.out.println(segmentTree.query(1, 3)); // Output: 17
    }
}
