package com.DataStructure;

import org.junit.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by xisisu on 10/31/16.
 */
public class HashTableOpenAddressing<T> {
    private static int INIT_CAPACITY = 3; // init to 2 for easy testing
    private static double INCREASE_THRESHOLD = 0.8; // double the size if threshold is reached
    private static double SHRINK_THRESHOLD = 0.1; // shrink the size if threshold is reached

    private List<T> _buckets;
    private List<Boolean> _deleted;
    private int _count = 0;

    // for test usage
    public int getCount() { return _count; }
    public int getSize() { return _buckets.size(); }
    public List getDeleted() { return _deleted; }

    public HashTableOpenAddressing() {
        _buckets = new ArrayList<>(Collections.nCopies(INIT_CAPACITY, null));
        _deleted = new ArrayList<>(Collections.nCopies(INIT_CAPACITY, false));
    }

    private boolean insertObjIntoBuckets(final T obj, final List<T> buckets) {
        Integer bucket = obj.hashCode() % buckets.size();
        Integer tryCount = 0;
        while (tryCount++ < buckets.size()) {
            if (obj.equals(buckets.get(bucket))) { // already exists
                return false;
            } else if (buckets.get(bucket) == null) { // found an empty slot, insert
                buckets.set(bucket, obj);
                return true;
            } else { // go to next bucket
                bucket = (bucket+1) % buckets.size();
            }
        }
        throw new RuntimeException("Should never go here for inserting " + obj + ", buckets: " + buckets);
    }

    public void insert(final T obj) {
        if (!insertObjIntoBuckets(obj, _buckets)) {
            return;
        }
        if (++_count >= (_buckets.size() * INCREASE_THRESHOLD)) {
            doubleSizeAndRehash();
        }
    }

    public void remove(final T obj) {
        Integer bucket = obj.hashCode() % _buckets.size();
        Integer tryCount = 0;
        while (tryCount++ < _buckets.size()) {
            if (obj.equals(_buckets.get(bucket))) { // found it!
                _buckets.set(bucket, null);
                _deleted.set(bucket, true);
                break;
            } else if (_buckets.get(bucket) == null && !_deleted.get(bucket)) { // found an empty slot, did not exist
                return;
            } else { // go to next bucket
                bucket = (bucket+1) % _buckets.size();
            }
        }

        if (--_count <= (_buckets.size() * SHRINK_THRESHOLD) &&
                _buckets.size() >= INIT_CAPACITY*2)
        {
            shrinkSizeAndRehash();
        }
    }

    public boolean exist(final T obj) {
        Integer bucket = obj.hashCode() % _buckets.size();
        Integer tryCount = 0;
        while (tryCount++ < _buckets.size()) {
            if (obj.equals(_buckets.get(bucket))) { // found it!
                return true;
            } else if (_buckets.get(bucket) == null && !_deleted.get(bucket)) { // found an empty slot, did not exist
                return false;
            } else { // go to next bucket
                bucket = (bucket+1) % _buckets.size();
            }
        }
        return false;
    }

    private void doubleSizeAndRehash() {
        final Integer newSize = _buckets.size() * 2;
        _deleted = new ArrayList<>(Collections.nCopies(newSize, false));

        final List<T> newBuckets = new ArrayList<>(Collections.nCopies(newSize, null));
        for (final T o : _buckets) {
            if (o == null) { continue; }
            insertObjIntoBuckets(o, newBuckets);
        }
        _buckets = newBuckets;
    }

    private void shrinkSizeAndRehash() {
        final Integer newSize = _buckets.size() / 2;
        _deleted = new ArrayList<>(Collections.nCopies(newSize, false));

        final List<T> newBuckets = new ArrayList<>(Collections.nCopies(newSize, null));
        for (final T o : _buckets) {
            if (o == null) { continue; }
            insertObjIntoBuckets(o, newBuckets);
        }
        _buckets = newBuckets;
    }

    @Override
    public String toString() {
        String result = "bucket size: " + _buckets.size() + ", count: " + _count + "\n";
        result += _buckets.toString();
        return result;
    }

    @Test
    public void testInsertMapToSameBucket() {
        final HashTableOpenAddressing<Integer> table = new HashTableOpenAddressing<>();
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
        final HashTableOpenAddressing<Integer> table = new HashTableOpenAddressing<>();
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
        final HashTableOpenAddressing<Integer> table = new HashTableOpenAddressing<>();
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
