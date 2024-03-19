package a1_2101140008;

import java.io.FileNotFoundException;
import java.util.List;

public class Result implements Comparable<Result> {
    public Doc d;
    public List<Match> matches;
    public int match_count;
    public int total_freq;
    public double avr_first_index;

    public Result(Doc d, List<Match> matches){
        this.d = d;
        this.matches = matches;
        int sum_first_index = 0;
        match_count = matches.size();
        for (int i = 0; i<match_count;i++){
            total_freq += matches.get(i).getFreq() ;
            sum_first_index+= matches.get(i).getFirstIndex();
        }
        avr_first_index = (double) (sum_first_index/match_count);

    }
    public List<Match> getMatches(){
        return matches;
    }
    public int getTotalFrequency(){
        return total_freq;
    }
    public double getAverageFirstIndex(){
        return avr_first_index;
    }
    public Doc getDoc() { return d; }
    public String htmlHighlight() throws FileNotFoundException {
        String word_highlight = "<h3>";
        List <Word> word_html = d.getTitle();
        for (Word wt : word_html) {
            boolean isMatch = matches.stream().anyMatch(match -> match.getWord().equals(wt));
            word_highlight += isMatch ? wt.getPrefix() + "<u>" + wt.getText() + "</u>" + wt.getSuffix() : wt.toString();
            word_highlight += " ";
        }
        word_html = d.getBody();
        word_highlight = word_highlight.trim()+ "</h3><p>";
        for (Word wb : word_html) {
            boolean match_w = false;
            for (Match match : matches) {
                if (match.getWord().equals(wb)) {
                    match_w = true;
                    break;
                }
            }
            if (match_w){
                word_highlight += wb.getPrefix() + "<b>" + wb.getText() + "</b>" + wb.getSuffix()+" ";
            }else {
                word_highlight += wb.toString() + " ";
            }

        }
        word_highlight = word_highlight.trim() + "</p>";
        return word_highlight;

    }


    @Override
    public int compareTo(Result o) {
        if (this.match_count != o.match_count){
            return Integer.compare(this.match_count,o.match_count);
        }
        if (this.total_freq != o.total_freq){
            return Integer.compare(this.total_freq,o.total_freq);
        }
        return Integer.compare((int) this.avr_first_index, (int) o.avr_first_index);

    }

}
