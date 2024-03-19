package a1_2101140008;

public class Match implements Comparable<Match>{
    public Doc d;
    public Word w;
    public int freq;
    public  int firstIndex;

    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.d = d;
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }

    public int getFreq() {
        return freq;
    }

    public int getFirstIndex(){
        return firstIndex;
    }
     public Word getWord(){
        return w;
     }


    @Override
    public int compareTo(Match o) {
        if (this.firstIndex > o.getFirstIndex()){
            return 1;
        } else if (this.firstIndex < o.getFirstIndex()) {
            return -1;
        }
        return 0;
    }


}
