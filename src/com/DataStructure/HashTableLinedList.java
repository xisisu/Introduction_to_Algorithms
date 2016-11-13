package com.DataStructure;

import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xisisu on 10/30/16.
 */
public class HashTableLinedList<T> {
    private static int INIT_CAPACITY = 3; // init to 2 for easy testing
    private static double INCREASE_THRESHOLD = 0.8; // double the size if threshold is reached
    private static double SHRINK_THRESHOLD = 0.1; // shrink the size if threshold is reached

    private List<List<T>> _buckets;
    private int _count = 0;

    // for test usage
    public int getCount() { return _count; }
    public int getSize() { return _buckets.size(); }

    // Note we cannot use the Collections.nCopies here, otherwise all buckets point to the same location!
    public HashTableLinedList() {
        _buckets = new ArrayList<>(INIT_CAPACITY);
        for (int i = 0; i < INIT_CAPACITY; ++i) {
            _buckets.add(new ArrayList<>());
        }
    }

    public void insert(final T obj) {
        final Integer bucket = obj.hashCode() % _buckets.size();
        if (_buckets.get(bucket).contains(obj)) { // already exist
            return;
        }

        _buckets.get(bucket).add(obj);
        if (++_count >= (_buckets.size() * INCREASE_THRESHOLD)) {
            doubleSizeAndRehash();
        }
    }

    public void remove(final T obj) {
        final Integer bucket = obj.hashCode() % _buckets.size();
        if (!_buckets.get(bucket).contains(obj)) { // does not exist
            return;
        }
        _buckets.get(bucket).remove(obj);
        if (--_count <= (_buckets.size() * SHRINK_THRESHOLD) &&
            _buckets.size() >= INIT_CAPACITY*2)
        {
            shrinkSizeAndRehash();
        }
    }

    public boolean exist(final T obj) {
        final Integer bucket = obj.hashCode() % _buckets.size();
        return !_buckets.get(bucket).isEmpty() && _buckets.get(bucket).contains(obj);
    }

    @Override
    public String toString() {
        String result = "bucket size: " + _buckets.size() + ", count: " + _count + "\n";
        for (int i = 0; i < _buckets.size(); ++i) {
            result += "bucket " + i + ": " + _buckets.get(i).toString() + "\n";
        }
        return result;
    }

    private void doubleSizeAndRehash() {
        final Integer newSize = _buckets.size() * 2;
        final List<List<T>> newBuckets = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; ++i) {
            newBuckets.add(new ArrayList<>());
        }

        for (final List lst : _buckets) {
            for (final Object o : lst) {
                final Integer bucket = o.hashCode() % newBuckets.size();
                newBuckets.get(bucket).add((T) o);
            }
        }
        _buckets = newBuckets;
    }

    private void shrinkSizeAndRehash() {
        final Integer newSize = _buckets.size() / 2;
        final List<List<T>> newBuckets = new ArrayList<>(newSize);
        for (int i = 0; i < newSize; ++i) {
            newBuckets.add(new ArrayList<>());
        }

        for (final List lst : _buckets) {
            for (final Object o : lst) {
                final Integer bucket = o.hashCode() % newBuckets.size();
                newBuckets.get(bucket).add((T) o);
            }
        }
        _buckets = newBuckets;
    }

    @Test public void testInsertMapToSameBucket() {
        final HashTableLinedList<Integer> table = new HashTableLinedList<>();
        // test Insert
        for (int i = 0; i < 5; ++i) {
            final Integer val = i*3;
            table.insert(val);
            table.insert(val); // insert multiple time results only one copy
            System.out.println("inserting " + val);
            System.out.println(table);
            System.out.println("");
            Assert.assertEquals(table.getCount(), i+1);
        }
    }

    @Test public void testDelete() {
        final HashTableLinedList<Integer> table = new HashTableLinedList<>();
        for (int i = 0; i < 5; ++i) {
            final Integer val = i*3;
            table.insert(val);
            table.insert(val); // insert multiple time results only one copy
        }

        for (int i = 4; i >= 0; --i) {
            final Integer val = i*3;
            table.remove(val);
            table.remove(val); // remove multiple time is fine
            System.out.println("removing " + val);
            System.out.println(table);
            System.out.println("");
            Assert.assertEquals(table.getCount(), i);
        }
    }

    @Test public void testExist() {
        final HashTableLinedList<Integer> table = new HashTableLinedList<>();
        for (int i = 0; i < 5; ++i) {
            final Integer val = i*3;
            table.insert(val);
            table.insert(val); // insert multiple time results only one copy
        }

        for (int i = 4; i >= 0; --i) {
            final Integer val = i*3;
            Assert.assertTrue(table.exist(val));
            table.remove(val);
            Assert.assertFalse(table.exist(val));
        }
    }
}