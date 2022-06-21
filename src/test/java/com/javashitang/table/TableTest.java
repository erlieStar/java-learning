package com.javashitang.table;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

/**
 * @Author lilimin
 * @Date 2022/6/10
 */
public class TableTest {

    @Test
    public void test1() {
        Table<Integer, Integer, Integer> table = HashBasedTable.create();

        table.put(1, 2, 3);
        table.put(1, 2, 4);
        table.put(1, 3, 4);
        table.put(1, 3, 5);
    }
}
