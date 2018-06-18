import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static <A,B> Iterable<Pair<A,B>> rectangle (Iterable<A> xs, Iterable<B> ys) {
        return new Iterable<Pair<A, B>>() {
            private Iterator<A> it1 = xs.iterator();
            private Iterator<B> it2 = ys.iterator();
            private B temp = it2.next();

            @Override
            public Iterator<Pair<A, B>> iterator() {
                return new Iterator<Pair<A, B>>() {
                    @Override
                    public boolean hasNext() {
                        if (!it2.hasNext()) {
                            /*if (it1.hasNext()) {
                                return it1.hasNext();
                            } else {
                                return false;
                            }*/
                            return it1.hasNext() ? it1.hasNext() : false;
                        } else {
                            return it2.hasNext();
                        }
                    }

                    @Override
                    public Pair<A, B> next() {
                        if(it1.hasNext()) {
                            return new Pair<>(it1.next(), temp);
                        } else {
                            it1 = xs.iterator();
                            temp = it2.next();
                            return new Pair<>(it1.next(), temp);
                        }
                    }
                };
            }
        };
    }

    public static void main(String [] args){
        ArrayList<Integer> xs = new ArrayList<>();
        xs.addAll(Arrays.asList(1,2));

        ArrayList<Integer> ys = new ArrayList<>();
        ys.addAll(Arrays.asList(1,2,3,4));

        for (Pair<Integer, Integer> p : rectangle(xs, ys)) {
            System.out.println(p);
        }
    }

}
