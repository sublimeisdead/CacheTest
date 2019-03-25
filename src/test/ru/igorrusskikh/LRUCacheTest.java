package ru.igorrusskikh;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {
    LRUCache lruCache=null;
    @BeforeEach
    void setUp() {
        lruCache=new LRUCache(2);
        lruCache.set(1,new Object());
        lruCache.set(2,new Object());
    }

    @AfterEach
    void tearDown() {
        lruCache=null;
    }

    @Test
    void get() {
        assertNotNull(lruCache.vals.get(1));
        assertNotNull(lruCache.vals.get(2));
    }

    @Test
    void set() {
        assertEquals(lruCache.vals.size(),2);
    }

    @Test
    void testCapacity(){
        lruCache.set(3,new Object());
        assertEquals(lruCache.vals.size(),2);

    }

    @Test
    void testEvictionStrategy(){
        lruCache.set(3,new Object());
        assertNull(lruCache.vals.get(1));

    }
}