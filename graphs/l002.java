import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class l002{
    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;
        }
    }
    public static void addEdge(ArrayList<Edge>[]graph,int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));
    }
    public static void display(ArrayList<Edge>[]graph){
        int N=graph.length;
        for(int i=0;i<N;i++){
            System.out.print(i+"->");
            for(Edge e:graph[i]){
                System.out.print("(" +e.v+","+e.w+")");
            }
            System.out.println();
        }
    }
    //o(E)
    public static int findEdge(ArrayList<Edge>[]graph,int u,int v){
        ArrayList<Edge>list=graph[u];
        for(int i=0;i<list.size();i++){
            Edge e=list.get(i);
            if(e.v==v){
                return i;
            }
        }
        return -1;
    }
    public static void removeEdge(ArrayList<Edge>[]graph,int u,int v){
        int idx=findEdge(graph,u,v);
        graph[u].remove(idx);
        idx=findEdge(graph,v,u);
        graph[v].remove(idx);

    }
    // O(E) where e is total no of edges in the particular component
    public static boolean dfs_findPath(ArrayList<Edge>[]graph,int src,int des,boolean vis){


        vis[src]=true;
        boolean res=false;
        for(Edge e: graph[src]){
            if(!vis[e.v]){
                res=res|| dfs_findPath(graph,e.v,des,vis);
            }
        }
        return res;
    }
    public static int printAllPaths(ArrayList<Edge>[]graph,int src,int des,String psf,int wsf,boolean[]vis){
        if(src==des){
            System.out.println(psf+"@"+wsf);
            return 1;
        }
        vis[src]=true;
        int count=0;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                count+=printAllPaths(graph,e.v,des,psf+e.v,wsf+e.e.w,vis);
            }
        }
        vis[src]=false;
        return count;
    }
    public static void dfs(ArrayList<Edge>[]graph,int src,boolean[]vis){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(vis[e.v]==false){
                dfs(graph,e.v,vis);
            }
        }
    }

    //O(E+V)
    public static void getConnectedComponent(ArrayList<Edge>[]graph){
        int components=0;
        int n=graph.length;
        boolean[]vis=new boolean[N];
        for(int i=0;i<N;i++){
            if(vis[i]==false){
                dfs(graph,i,vis);
                components++;
            }
        }
        System.out.println(components);
    }

    // O(E)
    public static void bfsForCycle(ArrayList<Edge>[]graph,int src,boolean[]vis){
        LinkedList<Integer>que=new LinkedList<>();
        int level=0;
        boolean isCycle=false;
        que.add(src);
        while(que.size()!=0){
            int size=que.size();
            System.out.print("Min No of Edges" +level+"->");
            while(size-->0){
                int rvtx=que.removeFirst();

                if(vis[rvtx]){
                    isCycle=true;
                    continue;
                }
                System.out.print(rvtx+" ");
                vis[rvtx]=true;
                for(Edge e:graph[rvtx]){
                    if(!vis[e.v]){
                        que.addLast(e.v);
                    }
                }
                

            }
            System.out.println();
            level++;
        }
    }
    //O(V)
    public static void bfsWithoutCycle(ArrayList<Edge>[]graph,int src,boolean[]vis){
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        int level=0;
        vis[src]=true;
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                int rvtx=que.removeFirst();
                System.out.print(rvtx+",");
                for(Edge e: graph[rvtx]){
                    if(!vis[e.v]){
                        vis[e.v]=true;
                        que.addLast(e.v);
                    }
                }
            }
            System.out.println();
            level++;
        }
    }
    public static boolean isBipartite(ArrayList<Edge>[]graph,int src,int []vis){
        LinkedList<Integer>que=new LinkedList<>();
        int color=0;
        que.add(src);
        boolean isCycle=false;
        boolean isBipartite=true;

        while (que.size()!=0){
            int size=que.size();
            while(size-->0){
                int rvtx=que.removeFirst();
                if(vis[rvtx]!=-1){
                    isCycle=true;
                    if(vis[rvtx]!=color){
                        isBipartite=false;
                        break;
                    }
                    continue;
                }
                vis[rvtx]=color;
                for(Edge e:graph[rvtx]){
                    if(vis[e.v]==-1){
                        que.addLast(e.v);
                    }
                }
            }
            color=(color+1)%2;
            if(!isBipartite) break
        }
        if(isCycle){
            if(isBipartite){
                System.out.println("Graph is Bi-Partite it means it has even length cycle")
            }else{
                System.out.println("Graph is Non Bi-Partite it means it has odd length cycle");
            }
        }else{
            System.out.println("Graph is Bi-Partite");
        }

    return isBipartite;

    }
public static boolean isBipartite(ArrayList<Edge>[]graph){
    int N=graph.length;
    int[]vis=new int[N];
    Arrays.fill(vis,-1);

    boolean res=true;
    for(int i=0;i<N;i++){
        if(vis[i]==-1){
            res=res&& isBipartite(graph,i,vis);
        }
    }
    return res;
}

    








}