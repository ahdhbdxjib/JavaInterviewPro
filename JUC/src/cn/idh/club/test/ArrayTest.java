package cn.idh.club.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
        int count = 1;

        while (list.size() != 1) {
            for (Iterator<Integer> i = list.iterator(); i.hasNext(); ) {
                count++;
                i.next();
                if (count == 2) {
                    i.remove();
                    count = 1;
                }

            }

        }

        System.out.println("list = " + list);
    }
}
