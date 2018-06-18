package aufgabe2;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static Iterable<Integer> merge (Iterable<Integer> xs, Iterable<Integer> ys) {
        return new Iterable<Integer>() {
            private Iterator<Integer> it1 = xs.iterator();
            private Iterator<Integer> it2 = ys.iterator();
            private int temp1 = it1.next();
            private int temp2 = it2.next();

            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        if (!it2.hasNext()) {
                            /*if (it1.hasNext()) {
                                return it1.hasNext();
                            } else {
                                return false;
                            }*/
                            return it1.hasNext();
                        } else {
                            return it2.hasNext();
                        }
                    }

                    @Override
                    public Integer next() {
                        //return temp1 < temp2 ? temp1 : temp2;

                        if (temp1 < temp2) {
                            int rueck = temp1;
                            if(it1.hasNext()) temp1 = it1.next();
                            return rueck;
                        } else {
                            int rueck = temp2;
                            if(it2.hasNext()) temp2 = it2.next();
                            return rueck;
                        }
                    }
                };
            }
        };
    }

    public static void main(String [] args){
        ArrayList<Integer> xs = new ArrayList<>();
        xs.addAll(Arrays.asList(3,5,8,9));

        ArrayList<Integer> ys = new ArrayList<>();
        ys.addAll(Arrays.asList(2,4,6));

        for (Integer p : merge(xs, ys)) {
            System.out.println(p);
        }
    }

}
