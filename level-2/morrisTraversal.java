import java.util.ArrayList;

public class morrisTraversal{
    public static class TreeNode{
        int val=0;
        TreeNode left=null;
        TreeNode right=null;

        TreeNode(int val){
            this.val=val;
        }
    }
    public static TreeNode getRightMostNode(TreeNode node,TreeNode curr){
        while(node.right!=null && node.right!=curr){
            node=node.right;
        }
        return node;
    }
    public static ArrayList<Integer> morrisInorderTraversal(TreeNode root){
        ArrayList<Integer>ans=new ArrayList<>();
        TreeNode curr=root;
        while(curr!=null){
            TreeNode left=curr.left;
            if(left==null){
                ans.add(curr.val);
                curr=curr.right;
            }else{
                TreeNode rightMostNode=getRightMostNode(left,curr);
                if(rightMostNode.right==null){
                    rightMostNode.right=curr;
                    curr=curr.left;
                }else{
                    rightMostNode.right=null;
                    ans.add(curr.val);
                    curr=curr.right;

                }
            }
        }
        return ans;
    }
}