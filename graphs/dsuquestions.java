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


}