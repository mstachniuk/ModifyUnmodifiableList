package com.blogspot.mstachniuk.modifyunmodifiablelist;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ModifyUnmodifiableListTest {

    final List<String> exampleList = new ArrayList<String>();

    @Before
    public void setUp() {
        exampleList.add("text 1");
        exampleList.add("text 2");
        exampleList.add("text 3");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionWhenModifyUnmodifiableList() {
        List<String> unmodifiableList = Collections.unmodifiableList(exampleList);
        unmodifiableList.clear();
    }

    @Test
    public void shouldModifyUnmodifiableList() {
        // given
        List<String> unmodifiableList = Collections.unmodifiableList(exampleList);

        // when
        exampleList.clear();

        // then
        assertEquals(0, unmodifiableList.size());
    }

    @Test
    public void shouldCloneUnmodifiableListToArray() {
        // given
        String[] unmodifiableTab = exampleList.toArray(new String[exampleList.size()]);

        // when
        exampleList.clear();

        // then
        assertEquals(3, unmodifiableTab.length);
        assertEquals("text 1", unmodifiableTab[0]);
    }

    @Test
    public void shouldCloneUnmodifiableListInForLoop() {
        // given
        List<String> unmodifiableList = new ArrayList<String>();
        for (String s : exampleList) {
            unmodifiableList.add(s);
        }

        // when
        exampleList.clear();

        // then
        assertEquals(3, unmodifiableList.size());
        assertEquals("text 1", unmodifiableList.get(0));
    }

    @Test
    public void shouldCloneUnmodifiableListWithSerializationUtils() {
        // given
        List<String> unmodifiableList = SerializationUtils.clone((ArrayList<String>) exampleList);

        // when
        exampleList.clear();

        // then
        assertEquals(3, unmodifiableList.size());
        assertEquals("text 1", unmodifiableList.get(0));
    }
}
