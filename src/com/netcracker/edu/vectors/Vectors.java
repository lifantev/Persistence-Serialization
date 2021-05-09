package com.netcracker.edu.vectors;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class Vectors {
    public static <T extends Number> Vector<Double> multByScalar(Vector<T> vector, double n) {
        Vector<Double> result = new Vector<>(vector.size());
        for (T t : vector) {
            result.add(n * t.doubleValue());
        }
        return result;
    }

    public static <T extends Number> Vector<Double> sum(Vector<T> v1, Vector<T> v2) {
        Vector<Double> result = null;
        if (v1.size() == v2.size()) {
            result = new Vector<>(v1.size());
            for (int i = 0; i < v1.size(); ++i) {
                result.add(v1.elementAt(i).doubleValue()
                        + v2.elementAt(i).doubleValue());
            }
        }
        return result;
    }

    public static <T extends Number> Vector<Double> multScalar(Vector<T> v1, Vector<T> v2) {
        Vector<Double> result = null;
        if (v1.size() == v2.size()) {
            result = new Vector<>(v1.size());
            for (int i = 0; i < v1.size(); ++i) {
                result.add(v1.elementAt(i).doubleValue()
                        * v2.elementAt(i).doubleValue());
            }
        }
        return result;
    }

    static <T extends Number> void outputVector(Vector<T> vector, OutputStream out) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeInt(vector.size());
            for (T t : vector) {
                objectOutputStream.writeDouble(t.doubleValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Vector<Double> inputVector(InputStream in) {
        Vector<Double> result = new Vector<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(in)) {
            int size = objectInputStream.readInt();
            for (int i = 0; i < size; ++i) {
                result.add(objectInputStream.readDouble());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static <T extends Number> void writeVector(Vector<T> vector, Writer out) {
        try {
            StringBuilder result = new StringBuilder();
            result.append(vector.size()).append(' ');
            for (T t : vector) {
                result.append(t).append(' ');
            }
            out.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Vector<Double> readVector(Reader in) {
        Vector<Double> result = new Vector<>();
        try {
            StreamTokenizer streamTokenizer = new StreamTokenizer(in);
            streamTokenizer.parseNumbers();
            streamTokenizer.nextToken();

            int size = 0;
            if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER)
                size = (int) streamTokenizer.nval;
            for (int i = 0; i < size; ++i) {
                streamTokenizer.nextToken();
                if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER)
                    result.add(streamTokenizer.nval);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static <T extends Number> String print(Vector<T> vector) {
        StringBuilder result = new StringBuilder(vector.size());
        for (T t : vector) {
            result.append(t.doubleValue()).append(' ');
        }
        return result.toString().trim();
    }

    public static void main(String[] args) throws IOException {
        Vector vector1 = new Vector(Arrays.asList(1, 2, 0));
        Vector vector2 = new Vector(Arrays.asList(1.2, -2.5, 10));
        Vector vector3 = new Vector(Arrays.asList("aknf", -2.5, 'q'));

        System.out.println("vector1 : " + print(vector1));
        System.out.println("vector2 : " + print(vector2));

        // Exception happens below because of vector not Number values.
        // System.out.println("vector3 : " + print(vector3));

        outputVector(sum(vector1, vector2), new FileOutputStream("file"));
        vector3 = inputVector(new FileInputStream("file"));
        System.out.println("vector3 = vector1 + vector2 : " + print(vector3));

        Writer w = new FileWriter("file");
        writeVector(multScalar(vector2, vector3), w);
        w.close();

        Reader r = new FileReader("file");
        vector2 = readVector(r);
        r.close();
        System.out.println("vector2 = (vector2, vector3) : " + print(vector2));
    }
}
