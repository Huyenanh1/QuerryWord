package a1_2101140008;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine {
    private List<Doc> docs = new ArrayList<>();
    public List<Result> results;

    public int loadDocs(String dirname) throws FileNotFoundException {
        File folder = new File(dirname);
        File[] listOfDocs = folder.listFiles();
        docs = new ArrayList<>();
        if (listOfDocs != null) {
            for (File file : listOfDocs) {
                Scanner s = new Scanner(new BufferedReader(new FileReader(file)));
                StringBuilder content = new StringBuilder();
                while (s.hasNext()){
                    content.append(s.nextLine()).append("\n");
                }
                docs.add(new Doc(content.toString()));
            }
            return docs.size();
        }
        return 0;
    }

    public Doc[] getDocs() {
        Doc[] d = new Doc[docs.size()];
        d = docs.toArray(d);
        return d;
    }



    public List<Result> search(Query q) throws FileNotFoundException {
        results = new ArrayList<>();
        boolean compare;
        for (Doc d : docs){
            List<Match> m = q.matchAgainst(d);
            if (!m.isEmpty()) {
                results.add(new Result(d, m));
            }
        }
        for (int i = 1; i < results.size(); i++) {
            compare = false;
            if (results.get(i - 1).compareTo(results.get(i)) < 0) {
                Result t = results.get(i - 1);
                results.set(i - 1, results.get(i));
                results.set(i, t);
                compare = true;
            }
            if (compare) {
                i = 0;
            }
        }
        return results;
    }
    public String htmlResult(List<Result> results) throws FileNotFoundException {
        String result = "";
        for (Result value : results) {
            result += value.htmlHighlight();
        }
        return result;
    }


}
