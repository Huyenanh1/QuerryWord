import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Query {
    public String searchPhrase;
    public List<Word> queryKey;

    public Query(String searchPhrase) throws FileNotFoundException {
        this.searchPhrase = searchPhrase;
        queryKey = new ArrayList<>();
        String[] keyword = searchPhrase.split(" ",searchPhrase.length());
        for (String key : keyword){
            boolean queryKeyword = Word.createWord(key).isKeyword();
            if (queryKeyword){
                queryKey.add(Word.createWord(key));
            }
        }
    }

    public List<Word> getKeywords(){
        return queryKey;
    }

    public List<Match> matchAgainst(Doc d) throws FileNotFoundException {
        List<Match> matchAgaint = new ArrayList<>();
        for (Word w : queryKey) {
            int firstIndex = 0;
            int freq = 0;
            for (int i = 0; i < d.getTitle().size(); i++) {
                if (w.equals(d.getTitle().get(i))) {
                    if (freq == 0) {
                        firstIndex = i + 1;
                    }
                    freq++;
                }
            }
            for (int i = 0; i < d.getBody().size(); i++) {
                if (w.equals(d.getBody().get(i))) {
                    if (freq == 0) {
                        firstIndex = d.getTitle().size() + i;
                    }
                    freq++;
                }
            }
            if (freq != 0) {
                matchAgaint.add(new Match(d, w, freq, firstIndex));
            }
        }
        for (int j = 1; j < matchAgaint.size(); j++ ){
            if (matchAgaint.get(j-1).compareTo(matchAgaint.get(j)) > 0){
                Match temp = matchAgaint.get(j-1);
                matchAgaint.set(j-1,matchAgaint.get(j));
                matchAgaint.set(j,temp);
            }
        }

        return matchAgaint;
    }



}


