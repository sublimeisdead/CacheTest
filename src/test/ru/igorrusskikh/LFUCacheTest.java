package ru.igorrusskikh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {
    LFUCache lfuCache=null;
    @BeforeEach
    void setUp() {
        lfuCache=new LFUCache(2);
        lfuCache.set(1,new Object());
        lfuCache.set(2,new Object());
    }

    @AfterEach
    void tearDown() {
        lfuCache=null;
    }

    @Test
    void get() {
        assertNotNull(lfuCache.vals.get(1));
        assertNotNull(lfuCache.vals.get(2));


    }

    @Test
    void set() {
        assertEquals(lfuCache.vals.size(),2);

    }

    @Test
    void testCapacity(){
        lfuCache.set(3,new Object());
        assertNull(lfuCache.vals.get(1));
        assertEquals(lfuCache.vals.size(),2);

    }

    @Test
    void testEvictionStrategy(){
        lfuCache.get(1);
        lfuCache.set(3,new Object());
        assertNull(lfuCache.vals.get(2));

    }


}