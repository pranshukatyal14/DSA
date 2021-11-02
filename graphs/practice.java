public class practice{

    public static class Edge{
        int v=0;
        int w=0;

        Edge(int v,int w){
            this.v=v;
            this.w=w;

        }
        public String toString(){
            return "(" + this.v +","+this.w+")";
        }
    }
    public static int N=7;
    public static ArrayList<Edge>[]graph=new ArrayList[N];

    public static void addEdge(int u,int v,int w){
        graph[u].add(new Edge(v,w));
        graph[v].add(new Edge(u,w));

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
    public static findEdge(int u,int v){
        for(int i=0;i<graph[u].size();i++){
            Edge e=graph[u].get(i);
            if(e.v==v){
                return i;
            }
        }
        return -1;
    }
    public static void removeEdge(int u,int v){
        int idx1=findEdge(u,v);
        int idx2=findEdge(v,u);

        graph[u].remove(idx1);
        graph[v].remove(idx2);
    }
    public static void removeVtx(int u){
        while(graph[u].size()!=0){
            int n=graph[u].size();
            Edge e=graph[u].get(n-1);
            removeEdge(u,e.v);
        }
    }
    public static boolean hasPath(int src,int des,boolean vis){
        if(src==des){
            return true;
        }
        boolean res=false;
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                res=res|| hasPath(e.v,des,vis);
            }
        }
            return res;
    }
    public static int printAllPaths(int src,int dest,boolean[]vis,String psf){
        if(src==dest){
            System.out.print(psf);
            return 1;
        }
        int count=0;
        vis[src]=true;
        for(Edge e: graph[src]){
            if(vis[e.v]==false){
                count+=printAllPaths(e.v,dest,vis,psf+e.v);
            }
        }
        vis[src]=false;
        return count;
    }
    public static void printPreOrder(int src,boolean[] vis,String ans,int wsf){

        System.out.println(src + "->"+ans+src+"@"+wsf);
        vis[src]=true;
        for(Edge e:graph[src]){
            if(vis[e.v]==false){
                printPreOrder(e.v,vis,ans+src,esf+e.w);
            }
        }
        vis[src]=false;
    }
    public static void printPostOrder(int src,boolean [] vis,String ans,int wsf){
        vis[src]=true;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                printPostOrder(e.v,vis,ans+src,wsf+e.w);
            }
        }
        System.out.println(src+"->"+ans+src+"@"+wsf);
        vis[src]=false;
    }

    public static void BFS(int src,boolean[]vis){
        int level=0;
        int cycleCount=0;
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        while(que.size()!=0){
            int size=que.size();
            System.out.print(level+"->");
            while(size-->0){
                int rvtx=que.removeFirst();
                if(vis[rvtx]){
                    cycleCount++;
                    continue;
                }
                System.out.print(rvtx+" ");
                vis[rvtx]=true;
                for(Edge e: graph[rvtx]){
                    if(vis[e.v]==false){
                        que.addLast(e.v);
                    }
                }
            }
            System.out.print();
            level++;
        }
    }
    public static void BFS_02(int src,boolean[]vis){
        int level=0;
        int cycleCount=0;
        LinkedList<Integer>que=new LinkedList<>();
        que.addLast(src);
        vis[src]=true;
        while(que.size()!=0){
            int size=que.size();
            System.out.print(level+"->");
            while(size-->0){
                int rvtx=que.removeFirst();
                System.out.print(rvtx+" ");
                for(Edge e:graph[rvtx]){
                    if(vis[e.v]==false){
                        que.addLast(e.v);
                        vis[e.v]=true;
                    }
                }

            }
            level++;
            System.out.println();
        }
    }
    public static boolean isBipartite(int src){
        LinkedList<Integer>que=new LinkedList<>();
        int[]vis=new int[N];
        Arrays.fill(vis,-1);
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
                for(Edge e:graph[rvtx]){
                    if(vis[e.v]==-1){
                        que.addLast(e.v);
                    }
                }
            }
            color=(color+1)%2;
        }
        return true;

    }
    public static boolean dfs_findPath(ArrayList<Edge>[]graph,int src,int dest,boolean []vis){

        vis[src]=true;
        boolean res=false;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                res=res|| dfs_findPath(graph,e.v,dest,vis);
            }
        }
        return res;
    }

    public static int printAllPaths(ArrayList<Edge>[]graph,int src,int dest,String psf,int wsf,boolean[]vis){
        if(src==dest){
            System.out.println(psf+dest+"@"+wsf);
            return 1;
        }
        
        vis[src]=true;
        int count=0;
        for(Edge e:graph[src]){
            if(!vis[e.v]){
                count+=printAllPaths(graph,e.v,dest,psf+e.v,wsf+e.w,vis);
            }
        }
        vis[src]=false;
        return count;
    }



}