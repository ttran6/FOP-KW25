package aufgabe2;

import java.util.*;

public class FloMain {
    private static int toAdd = -1;
    private static Boolean keepGoing = true;

    /**
     * FIXME funktioniert nicht, wenn eine zahl mehr als einmal vorkommt
     * @param xs Liste an Integer
     * @param ys Liste an Integer
     * @return Iterator über beide Listen
     */
    static Iterable<Integer> merge(Iterable<Integer> xs, Iterable<Integer> ys) {
        return () -> new Iterator<Integer>() {
            private Iterator<Integer> it1 = xs.iterator();
            private Iterator<Integer> it2 = ys.iterator();

            @Override
            public boolean hasNext() {
                return keepGoing;
            }

            @Override
            public Integer next() {
                if (it1.hasNext() && !it2.hasNext()) return it1.next();
                if (it2.hasNext() && !it1.hasNext()) return it2.next();
                if (toAdd != -1) {
                    if (!it2.hasNext() && !it1.hasNext()) keepGoing = false;
                    int toReturn = toAdd;
                    toAdd = -1;
                    return toReturn;
                }

                int left = it1.next();
                int right = it2.next();

                toAdd = left>right ? left : right;
                return left<right ? left : right;
                /*
                if (leftNext) {
                    left = it1.next();
                    if (rightNext) {
                        right = it2.next();
                        toAdd = left>right ? left : right;
                        return left<right ? left : right;
                    } else if (toAdd<left) {
                        int toReturn = toAdd;
                        toAdd = left<toAdd ? toAdd : left;
                        return toReturn;
                    } else return left;
                } else return it2.next();
                */
            }
        };
    }

    public static void main(String[] args) {
        ArrayList<Integer> xs = new ArrayList<>();
        xs.addAll(Arrays.asList(3,5,8,9));

        ArrayList<Integer> ys = new ArrayList<>();
        ys.addAll(Arrays.asList(2,4,6,7,42));

        ArrayList<Integer> result = new ArrayList<>();

        System.out.println("Left list: " + xs);
        System.out.println("Right list: " + ys);

        for (int i : merge(xs,ys)) {
            result.add(i);
        }
        toAdd++;
        System.out.println("Result list: " + result);
    }
}