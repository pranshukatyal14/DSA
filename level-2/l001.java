import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Hashset;
import java.util.LinkedList;

public class l001{
    publuc static class TreeNode{
        int val=0;
        TreeNode left=null;
        TreeNode right=null;

        TreeNode(int val){
            this.val=val;
        }
    }

    public static int size(TreeNode root){
        return root==null ? 0: size(root.left)+size(root.right)+1;
    }
    public static int height(TreeNode root){
        return root==null -1:Math.max(height(root.left),height(root.right))+1;
    }
    public static int maximum(TreeNode root){
        return root==null?-(int)1e9:Math.max(Math.max(maximum(root.left),maximum(root.right)),root.val);
    }
    public static boolean find(TreeNode root,int data){
        if(root==null){
            return false;
        }
        boolean res=root.val==data;
        return res || find(root.left,data) || find(root.right,data);
    }
    public static ArrayList<TreeNode>nodetoRootPath(TreeNode root,int data){
        if(root==null){
            return new ArrayList<>();
        }
        if(root.val==data){
            ArrayList<TreeNode>base=new ArrayList<>();
            base.add(root);
            return base;
        }

        ArrayList<TreeNode>left=nodetoRootPath(root.left,data);
        if(left.size()!=0){
            left.add(root);
            return left;
        }

        ArrayList<TreeNode>right=nodetoRootPath(root.right,data);
        if(right.size()!=0){
            right.add(root);
            return root;
        }
    }
    return new ArrayList<>();
    public static boolean nodetoRootPath(TreeNode root,int data,ArrayList<TreeNode>ans){
        if(root==null){
            return false;
        }
        boolean res=(root.val==data) || nodetoRootPath(root.left,data,ans) ||nodetoRootPath(root.right,data,ans);
        if(res){
            ans.add(root);
            
        }
        return res;
    }
    public static void rootToAllLeafPath(TreeNode root,ArrayList<Integer>smallAns,ArrayList<ArrayList<Integer>>ans){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            ArrayList<Integer>base=new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return ans;
        }
        smallAns.add(root.val);
        rootToAllLeafPath(root.left,smallAns,ans);
        rootToAllLeafPath(root.right,smallAns,ans);
        smallAns.remove(smallAns.size()-1);
    }





    public static ArrayList<ArrayList<Integer>>rootToAllLeafPath(TreeNode root){
        ArrayList<Integer>smallAns=new ArrayList<>();
        ArrayList<ArrayList<Integer>>ans=new ArrayList<>();
        rootToAllLeafPath(root,smallAns,ans);
        return ans;
    }

    public static void singleChildNodes(TreeNode root,ArrayList<Integer>ans){
        if(root==null || (root.left==null && root.right==null )){
            return ;
        }
        if(root.left==null || root.right==null){
            ans.add(root.val);
        }
        singleChildNodes(root.left,ans);
        singleChildNodes(root.right,ans);

    }
    public static int countSingleChildNodes(TreeNode root){
        if(root==null || (root.left==null && root.right==null)){
            return 0;
        }
        int left=countSingleChildNodes(root.left);
        int right=countSingleChildNodes(root.right);
        int ans=left+right;
        if(root.left==null || root.right==null){
            ans++;
        }
        return ans;



    }
    public static void kdown(TreeNode root,TreeNode blockNode,int k,List<Integer>ans){
        if(root==null || root==blockNode ||k<0){
            return;
        }

        if(k==0){
            ans.add(root.val);
            return;
        }
        kdown(root.left,blockNode,k-1,ans);
        kdown(root.right,blockNode,k-1,ans);

    }
    public List<Integer> distanceK(TreeNode root,TreeNode target,int k){
        ArrayList<Integer>path=new ArrayList<>();
        nodetoRootPath(root,target.val,path);
        List<Integer>ans=new ArrayList<>();
        TreeNode blockNode=null;
        for(int i=0;i<path.size();i++){
            kdown(path.get(i),blockNode,k-i,ans);
            blockNode=path.get(i);
        }
        return ans;
    }

public TreeNode lowestCommmonAncestor(TreeNode node,int p,int q){
    ArrayList<TreeNode>list1=new ArrayList<>();
    ArrayList<TreeNode>list2=new ArrayList<>();
    nodetoRootPath(node,p,list1);
    nodetoRootPath(node,q,list2);
    int i=list1.size()-1;
    int j=list2.size()-1;
TreeNode lca=null;
while(i>=0 && j>=0){
    if(list1.get(i)!=list2.get(j)){
        break;
    }
    lca=list1.get(i);
    i--;
    j--;
}
return lca;
}
public static TreeNode LCA=null;
public boolean lowestCommmonAncestor_(TreeNode root,TreeNode p,TreeNode q){
if(root==null){
    return false;
}
    boolean self=false;
    if(root==p || root==q){
        self=true;
    }


    boolean left=lowestCommmonAncestor_(root.left,p,q);
    boolean right=lowestCommmonAncestor_(root.right,p,q);

    if((self && left )|| (self && right)|| (left&& right)){
        LCA=node;
    }
    return left|| right|| self;


}
public TreeNode lowestCommmonAncestor1(TreeNode root,TreeNode p,TreeNode q){
    lowestCommmonAncestor_(root,p,q);
    return LCA;
}

public static void printSingleChildNodes(TreeNode node,TreeNode parent){
    if(node==null){
        return;
    }
    if(parent!=null &&(parent.left==null || parent.right==null)){
        System.out.println(node.val);
    }

    printSingleChildNodes(node.left,node);
    printSingleChildNodes(node.right,node);
}
public static TreeNode removeLeaves(TreeNode node){
    if(node==null){
        return null;
    }
    if(node.left==null && node.right==null){
        return null;
    }
    removeLeaves(node.left);
    removeLeaves(node.right);
}
public static void removeLeaves_(TreeNode root,TreeNode par){
    if(root==null){
        return;
    }

    if(root.left==null && root.right==null){
        if(par.left==root){
            par.left=null;
        }else{
            par.right=null;
        }
    }
    removeLeaves_(root.left,root);
    removeLeaves_(root.right,root);

}
public static TreeNode removeLeaves(TreeNode root){
    if(root.left==null && root.right==null){
        return null;
    }
    removeLeaves_(root,null);
    return root;
}
public static TreeNode prev= null; 
public static boolean isBst(TreeNode root){
    if(node==null){
        return true;
    }
    boolean left=isBst(root.left);
    if(!left){
        return false;
    }
    if(prev!=null && prev.val>root.val){
        return false;
    }

    boolean right=isBst(root.right);
    if(!right){
        return false;
    }
    return true;
}

}