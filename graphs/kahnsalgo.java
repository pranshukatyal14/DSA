public class kahnsalgo{
    public static ArrayList<Integer>kahnsalgo(ArrayList<Edge>[]graph){
        int N=graph.length;
        LinkedList<Integer>que=new LinkedList<>();
        ArrayList<Integer>ans=new ArrayList<>();

        int[] indegree=new int[N];
        for(int i=0;i<N;i++){
            for(Edge e:graph[i]){
                indegree[e.v]++;
            }
        }
        for(int i=0;i<N;i++){
            if(indegree[i]==0){
                que.addLast(i);
            }
        }
        while(que.size()!=0){
            int rvtx=que.removeFirst();
            ans.add(rvtx);
            for(Edge e:graph[rvtx]){
                if(--indegree[e.v]==0){
                    que.addLast(e.v);
                }
            }
           
        }
        if(ans.size()!=N){
            ans.clear();
        }
        return ans;
    }
}