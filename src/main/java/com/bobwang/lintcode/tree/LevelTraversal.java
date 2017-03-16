package com.bobwang.lintcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by cwang on 3/16/17.
 */
public class LevelTraversal {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList result = new ArrayList();
        // write your code here
        if(root==null){
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()){
            ArrayList<Integer> level = new ArrayList<Integer>();
            
        }
    }
}
