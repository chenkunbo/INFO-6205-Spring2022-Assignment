package HW05_Vertical_Order_Traversal_of_a_Binary_Tree_987;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Solution {
    class Point{
        int x;
        int y;
        int val;
        public Point(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalTraversal(TreeNode root){
        if(root == null)
            return new ArrayList();

        List<Point> list = new ArrayList();
        vtUtil(root, 0, 0, list);

        //Sort points based on x then y then value
        Collections.sort(list, (p1, p2) -> p1.x == p2.x ? p1.y == p2.y ? p1.val - p2.val : p2.y - p1.y : p1.x - p2.x);

        //Build treeMap with key as x value of node
        Map<Integer, List<Integer>> map = new TreeMap();
        for(Point p: list){
            List<Integer> line = map.getOrDefault(p.x, new ArrayList());
            line.add(p.val);
            map.put(p.x, line);
        }

        //Construct result List<List<Integer>>
        List<List<Integer>> result = new ArrayList();
        for(List<Integer> l: map.values())
            result.add(l);

        return result;

    }
    private void vtUtil(TreeNode root, int x, int y, List<Point> list){
        if(root == null)
            return;
        list.add(new Point(x, y, root.val));
        vtUtil(root.left, x - 1, y - 1, list);
        vtUtil(root.right, x + 1, y - 1, list);
    }


}



