public class dfs_topo_cycle{

    // detect cycle using dfs
   
    public static boolean dfs_topo_cycle(ArrayList<Edge>[]graph,int src,int[]vis,ArrayList<Integer>ans){
        vis[src]=0;
        boolean res=false;
        for(Edge e:graph[src]){
            if(vis[e.v]==-1){
                res=res| dfs_topo_cycle(graph,e.v,vis,ans);
            }else if(vis[e.v]==0){
                return true;
            }
        }
        vis[src]=1;
        ans.add(src);
        return res;
    }


   public static ArrayList<Integer>dfs_topo_cycle(ArrayList<Edge>[]graph){
       int N=graph.length;
       int[]vis=new int[N];
       Arrays.fill(vis,-1);
       ArrayList<Integer>ans=new ArrayList<>();
        boolean cycle=false;
        for(int i=0;i<N;i++){
            if(vis[i]==-1){
                cycle=cycle|| dfs_topo_cycle(graph,i,vis,ans);
            }
        }
        if(cycle){
            ans.clear();
        }
        return ans;
   }
}