package a1_2101140008;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Doc {
    public String content;
    public List<Word> title = new ArrayList<>();
    public List<Word> body = new ArrayList<>();
    public Doc (String content) throws FileNotFoundException {
        this.content = content;
        String [] contents = content.split("\n");
        if (contents.length == 2){
            String [] docKey = contents[0].split(" ");
            for (String d : docKey){
                title.add(Word.createWord(d));
            }
            docKey = contents[1].split(" ");
            for (String d : docKey){
                body.add(Word.createWord(d));
            }
        }
    }

    public List<Word> getTitle(){
        return title;
    }
    public List<Word> getBody(){
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doc doc = (Doc) o;
        return Objects.equals(content, doc.content);
    }




}
