package sample.spl1.FuzzyLogic;

import java.util.*;

public class FuzzyVariable {

    protected ArrayList list = new ArrayList();

    public void add(TriangleFuzzySet set)
    {
        list.add(set);
    }

    public void clear()
    {
        list.clear();
    }

    public double []evaluate(double in)
    {
        double result[] = new double[list.size()];
        Iterator itr = list.iterator();
        int i = 0;
        while (itr.hasNext()) {
            TriangleFuzzySet set = (TriangleFuzzySet)itr.next();
            result[i++] = set.evaluate(in);
        }
        return result;
    }


}
