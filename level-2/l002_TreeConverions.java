import java.util.LinkedList;
public class l002_TreeConversions{
    public static class TreeNode{
        int val=0;
        TreeNode left=null;
        TreeNode right=null;

        TreeNode(int val){
            this.val=val;
        }
     }
    public static class Node{
        int val=0;
        Node left=null;
        Node right=null;

        Node(int val){
            this.val=val;
        }
    }

    public static Node prev=null;
    public static void bTodll_(Node root){
        if(root==null){
            return;
        }
        bTodll_(root.left);
        prev.right=root;
        root.left=prev;
        prev=root;
        bTodll_(root.right);
    }
    public static Node bTodll(Node root){
    Node dummy=new Node(-1);
    prev=dummy;
    bTodll_(root);

    Node head=dummy.right;
    head.left=dummy.right=null;
    return head;
    
}

    //.......................
    public static void insertAllLeft(Node root,LinkedList<Node>st){
        Node curr=root;
        while(curr!=null){
            st.addFirst(curr);
            curr=curr.left;
        }
    }
    public static Node bTodll(Node root){
        LinkedList<Node>st=new LinkedList<>();
        insertAllLeft(root,st);
        Node dummy=new Node(-1);
        Node prev=dummy;
        while(st.size()!=0){
            Node curr=st.removeFirst();
            prev.right=curr;
            curr.left=prev;
            prev=curr;
            insertAllLeft(curr.right,st);
        }
        Node head=dummy.right;
        head.left=dummy.right=null;
        return head;
    }
    public static Node bTreeToClist(Node root){
        Node dummy=new Node (-1);
        prev=dummy;
        bTodll_(root);
        Node head=dummy.right;
        head.left=dummy.right=null;

        prev.right=head;
        head.left=prev;


        return head;
    }
    public static Node getMidNode(Node node){
        if(node==null || node.right==null){
            return node;
        }
        Node slow=node;
        Node slow=node;
        while(fast.right!=null && fast.right.right!=null){
            slow=slow.right;
            fast=fast.right.right;

        }
        return slow;
    }
    public static mergeTwoDll(Node h1,Node h2){
        if(h1==null || h2==null){
            return h1!=null?h1:h2;

        }
        Node dummy=new Node(-1);
        Node prev=dummy;
        Node c1=h1,c2=h2;
        while(c1!=null && c2!=null){
            if(c1.val<=c2.val){
                prev.right=c2;
                c1.left=prev;
                c1=c1.next;
            }else{
                prev.right=c2;
                c2.left=prev;
                c2=c2.right;
            }
            prev=prev.right;
        }
        if(c1!=null){
            prev.right=c1;
            c1.left=prev;
        }else{
            prev.right=c2;
            c2.left=prev;
        }
        Node head=dummy.right;
        dummy.right=head.left=null;
        return head;

    }
    public static Node sortDll(Node head){
        if(head==null || head.right==null){
            return head;
        }
        Node midNode= getMidNode(head);
        Node nHead=midNode.right;
        nhead.left=midNode.right=null;

        return mergeTwoDll(sortDll(head),sortDll(nhead));
    }
    public static Node sortedDllToBst(Node head){
        if(head==null || head.right==null){
            return head;
        }
        Node root=getMidNode(head);
        Node leftHead=head;
        Node rightHead=root.right;

        root.left.right=root.right.left=null;
        root.left=root.right=null;
        
        root.left=sortedDllToBst(leftHead);
        root.right=sortedDllToBst(rightHead);
        return root;
    }
    public static Node btTOBST(Node root){
        Node head=bTodll(root);
        head=sortDll(head);
        return sortedDllToBst(head);
    }
 }