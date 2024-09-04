package com.nt.binary.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Enzo
 * @date : 2024/9/4
 */
public class NumTree {
    public int numTrees(int n) {
        return recur(n, new HashMap<>());
    }
    public int recur(int n, Map<Integer, Integer> map){
        if (n <= 1){
            map.put(n, 1);
            return 1;
        }
        if (map.containsKey(n)){
            return map.get(n);
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            //
            sum += recur(i - 1, map) * recur(n - i, map);
        }
        map.put(n, sum);

        return sum;

    }
}
