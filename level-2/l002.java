import java.util.ArrayList;
import java.util.LinkedList;

public class l002{
    public static class TreeNode{
        int val=0;
        TreeNode left=null;
        TreeNode right=null;

        TreeNode(int val){
            this.val=val;
        }
    }
    public static int size(TreeNode root){
        return root==null ?-1: size(root.left)+size(root.right)+1;

    }
    public static int height(TreeNode root){
        return root==null?-1:Math.max(height(root.left),height(root.right))+1;
    }
    public static int maximum(TreeNode root){
        TreeNode curr=root;
        while(curr.right!=null){
            curr=curr.right;
        }
        return curr.val;
    }
    public static int minimum(TreeNode root){
        TreeNode curr=root;
        while(curr.left!=null){
            curr=curr.left;
        }
        return curr.val;
    }
    public static boolean find(TreeNode root,int data){
        TreeNode curr=root;
        while(curr!=null){
            if(curr.val==data){
                return true;
            }
            elseif(curr.val>data){
                curr=curr.left;
            }else{
                curr=curr.right;
            }
        }
    }
        return false;
        public static ArrayList<TreeNode>rootToNodePath(TreeNode root,int data){
            ArrayList<TreeNode>ans=new ArrayList<>();
            TreeNode curr=root;
            boolean flag=false;
            while(curr!=null){
                ans.add(curr);
                if(curr.val==data){
                    flag=true;
                    break;
                }else if(curr.val<data){
                    curr=curr.right;
                }else{
                    curr=curr.left;
                }
            }
            if(!flag){
                ans.clear();
            }
            return ans;

    }
    public TreeNode lowestCommmonAncestor(TreeNode root,int p,int q){
        TreeNode curr=root;
        while(curr!=null){
            if(root.val<p && root.val<q){
                curr=curr.right;
            }else if(root.val>p && root.val>q){
                curr=curr.left;
            }else{
                return curr;
            }
        }
        return null;
    }



}