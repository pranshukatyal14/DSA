public class practicedirected{
    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    
    public String toString(){
        return "("+this.v+","+this.w+")";
    }
}

public static int N=7;
public static ArrayList<Edge>[]graph=new ArrayList[N];
public static void addEdge(int u,int v,int w){
    graph[u].add(new Edge(v,w));
}

public static void display(){
    for(int i=0;i<N;i++){
        System.out.print(i+"->");
        for(Edge e:graph[i]){
            System.out.print(e);
        }
        System.out.println();
    }
}
public static void dfs_topo(int src,boolean[] vis,ArrayList<Integer>ans){
    vis[src]=true;
    for(Edge e:graph[src]){
        if(!vis[e.v]){
            dfs_topo(e.v,vis,ans);
        }
    }
    ans.add(src);
}

public static void topologicalSort(){
    boolean[] vis=new boolean[N];
    ArrayList<Integer>ans=new ArrayList<>();
    for(int i=0;i<N;i++){
        if(!vis[i]){
            dfs_topo(i,vis,ans);
        }
    }
}

public boolean isBipartite(int[][]graph,int src,int[]vis){
    // -1->undefine , 0-> red,1->green

    LinkedList<Integer>que=new LinkedList<>();
    que.addLast(src);
    int color=0;

    while(que.size()!=0){
        int size=que.size();
        while(size-->0){
            int rvtx=que.removeFirst();
            if(vis[rvtx]!=-1){
                if(vis[rvtx]!=color){
                    return false;
                }
                continue;
            }
            vis[rvtx]=color;
            for(int v: graph[rvtx]){
                if(vis[v]==-1){
                    que.addLast(v);
                }

            }
            
        }
        color=(color+1)%2;
    }
    return true;
}

public boolean isBipartite(int[][]graph){
    int N=graph.length;
    int[]vis=new int[N];
    Arrays.fill(vis,-1);

    for(int i=0;i<N;i++){
        if(vis[i]==-1 && !isBipartite(graph,i,vis));
            return false;
    }
    return true;
}










}