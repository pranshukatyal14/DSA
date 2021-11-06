public class dsuquestions{
    public static int[]par,size;
    public static int findPar(int u){
        return par[u]==u?u:(par[u]=findPar(par[u]));
    }
    public static String smallestEquivalentString(String s1,String s2,String baseStr){
        par=new int[26];
        for(int i=0;i<26;i++){
            par[i]=i;
        }
        for(int i=0;i<s1.length();i++){
            int p1=findPar(s1.charAt(i)-'a');
            int p2=findPar(s2.charAt(i)-'a');

            par[p1]=Math.min(p1,p2);
            par[p2]=Math.min(p1,p2);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<baseStr.length();i++){
            char ch=(char)(findPar(baseStr.charAt(i)-'a')-'a');
            sb.append(ch);
        }
        return sb.toString();

    }
    // no of inlands -2 (locked)

    int par[],suze;

    public List<Integer> numIsland2(int n,int m,int[][]positions){
        List<Integer>ans=new ArrayList<>();
        int[][]dir={{1,0},{-1,0},{0,1},{0,-1}};
        par=new int[n*m];
        Arrays.fill(par,-1);
        int count=0;
        for(int[]p:positions){
            int i=p[0],j=p[1];
            if(par[i*m+j]==-1){
                count++;
                par[i*m+j]=i*m+j;
                int p1=findPar(i*m+j);
                for(int d=0;d<4;d++){
                    int r=i+dir[d][0];
                    int c=j+dir[d][1];
                    if(r>=0 && c>=0 && r<n && c<m && par[r*m+c]!=-1){
                        int p2=findPar(r*m+c);
                        if(p1!=p2){
                            count--;
                            par[p2]=p1;
                        }
                    }
                }
                
                
            }
            ans.add(count);

            
        }
        return ans;
    }

    // mr president
    public int mrpresident(int[][]edges,int N,int k){
        par=new int [N+1];
        for(int i=0;i<=N;i++){
            par[i]=i;
        }
        Arrays.sort(edges,(a,b)->{
            return a[2]-b[2];
        });
        ArrayList<Integer>roads=new ArrayList<>();
        int components=N;
        int mcost=0;
        for(int[]e:edges){
            int u=e[0];
            int v=e[1];
            int w=e[2];

            int p1=findPar(u);
            int p2=findPar(v);
            if(p1!=p2){
                par[p1]=p2;
                components--;
                mcost+w;
                roads.add(w);
            }

        }
        if(components>1){
            return -1;
        }
        int superRoad=0;
        for(int i=roads.size()-1;i>=0;i--){
            if(mcost<=k){
                break;
            }
            mcost=mcost-roads.get(i)+1;
            superRoad++;
        }
        return mcost<=k?superRoad:-1;
    }



}