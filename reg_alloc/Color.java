package reg_alloc;

import graph.Node;

import java.util.Hashtable;

import Temp.Temp;
import Temp.TempMap;
import util.List;

class Color implements TempMap
{
    Color(InterferenceGraph ig, TempMap initial, List<Temp> registers, 
            Hashtable<Node, Integer> cost)
    {
        super();
    }

    public String tempMap(Temp t)
    {
        return null;
    }

    List<Temp> spills()
    {
        return null;
    }
}
