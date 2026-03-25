class Solution {
    public String[] findWords(String[] words) {
        String row1="qwertyuiop",row2="asdfghjkl",row3="zxcvbnm";
        List<String> res=new ArrayList<>();

        for(String w:words){
            String lower=w.toLowerCase();
            if(check(lower,row1)||check(lower,row2)||check(lower,row3))
                res.add(w);
        }
        return res.toArray(new String[0]);
    }

    boolean check(String w,String row){
        for(char c:w.toCharArray())
            if(row.indexOf(c)==-1) return false;
        return true;
    }
}