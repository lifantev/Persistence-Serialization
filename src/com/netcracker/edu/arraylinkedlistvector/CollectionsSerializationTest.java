package com.netcracker.edu.arraylinkedlistvector;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.LinkedList;

/**
 * Class for testing CollectionsSerialization abilities.
 */
class CollectionsSerializationTest {
    /**
     * Test of ArrayVector serialization.
     * Writes ArrayVector to file, then reads it from that file
     * and compares with the original object (based on saved values).
     */
    @Test
    void testArrayVector() {
        CollectionsSerialization.ArrayVector arrayVectorWrite =
                new CollectionsSerialization.ArrayVector(new int[]{1, 3, 5});

        try (ObjectOutputStream objOutStream =
                     new ObjectOutputStream(new FileOutputStream("file"))) {
            objOutStream.writeObject(arrayVectorWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CollectionsSerialization.ArrayVector arrayVectorRead =
                new CollectionsSerialization.ArrayVector();
        try (ObjectInputStream objInStream =
                     new ObjectInputStream(new FileInputStream("file"))) {
            arrayVectorRead = (CollectionsSerialization.ArrayVector) objInStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert (arrayVectorRead.equals(arrayVectorWrite));
    }

    /**
     * Test of LinkedList serialization.
     * Writes LinkedList to file, then reads it from that file
     * and compares with the original object (based on saved values).
     */
    @Test
    void testLinkedListVector() {
        LinkedList linkedListWrite = new LinkedList();
        linkedListWrite.add("first");
        linkedListWrite.add("second");

        try (ObjectOutputStream objOutStream =
                     new ObjectOutputStream(new FileOutputStream("file"))) {
            objOutStream.writeObject(linkedListWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LinkedList linkedListRead = null;
        try (ObjectInputStream objInStream =
                     new ObjectInputStream(new FileInputStream("file"))) {
            linkedListRead = (LinkedList) objInStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert (linkedListRead.equals(linkedListWrite));
    }

}