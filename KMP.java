//首先需要建立模式串的最长公共前后缀数组 在匹配的时候确定回退位置
class Solution {
    //首先是建立next数组，这里存储的值为前i个字符的最长公共缀的个数 -1
    public void getNext(int[] next, String s){
      //j指前缀末尾位置  i指后缀末尾位置
        int j = -1;//j也是最长公共的前后缀长度
        next[0] = j;//初始化next[0]为-1，则表示最长公共的前后缀长度为0
        //从下标1开始进行判断  比较前缀末尾元素与后缀末尾元素是否一致
        for (int i = 1; i < s.length(); i++){
            //如果所指字符不相同 j就回退至其前一个下标位置对应的值
            while(j>=0 && s.charAt(i) != s.charAt(j+1)){
                j=next[j];
            }
            //如果所指字符相同，j++ 表示最长公共的前后缀长度+1
            if(s.charAt(i)==s.charAt(j+1)){
                j++;
            }
            //将当前最长公共的前后缀长度保存至i位置 
            next[i] = j;
        }
    }
  
    public int strStr(String haystack, String needle) {
      //模式串长度为0 返回0  
      if(needle.length()==0){
            return 0;
        }
        //next存储最长相等前后缀的个数,方便回退
        int[] next = new int[needle.length()];
        //对needle生成next数组
        getNext(next, needle);
        int j = -1;
        //遍历目标串
        for(int i = 0; i < haystack.length();i++){
            //如果目标串位置元素与模式串位置元素不相同 则回退
            while(j >= 0 && haystack.charAt(i) != needle.charAt(j+1)){
                j = next[j];
            }
            //两元素相等 j向右移动
            if(haystack.charAt(i)==needle.charAt(j+1)){
                j++;
            }
            //若j移动到模式串最后位置 说明目标串中存在模式串 并返回第一次出现的下标
            if(j == needle.length()-1){
                return (i-needle.length()+1);
            }
        }

        return -1;
    }
}

