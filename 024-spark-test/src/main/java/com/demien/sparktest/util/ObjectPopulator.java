package com.demien.sparktest.util;

import com.demien.sparktest.domain.IPersistable;
import com.demien.sparktest.domain.Item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObjectPopulator {

    interface RandomGenerator {
        Object getRandomValue();
    }

    enum DataType {
        Integer(() -> {
            return new Integer((int) (Math.random() * 1000));
        }),
        Long(() -> {
            return new Long((long) (Math.random() * 1000));
        }),
        Date(()-> {
            return new Date(new Date().getTime() - (int) (Math.random() * 1000 * 60 * 60 * 24 * 100));
        }),
        String(() -> {
            StringBuffer result = new StringBuffer();
            String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G"};
            int length = (int) (Math.random() * 15) + 5;
            for (int i = 0; i < length; i++) {
                int pos = (int) (Math.random() * letters.length);
                result.append(letters[pos]);
            }
            return result.toString();
        }
        );

        private RandomGenerator generator;

        DataType(RandomGenerator generator) {
            this.generator = generator;
        }

        Object getRandomValue() {
            return generator.getRandomValue();
        }
    }

    public static Object populate(IPersistable instance) throws IllegalAccessException {
        List<Field> fields = getAllFields(instance);
        for (Field eachField : fields) {
            eachField.setAccessible(true);
            String typeName=eachField.getType().getSimpleName();
            if (eachField.getType().getTypeName().startsWith("com.demien")) {
                Object obj=null;
                try {
                     obj=eachField.getType().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
                obj=populate((IPersistable) obj);
                eachField.set(instance, obj);
            } else {
                DataType dataType = DataType.valueOf(typeName);
                eachField.set(instance, dataType.getRandomValue());
            }
        }
        return instance;
    }


    private static List<Field> getAllFields(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        List<Field> result = new ArrayList<Field>();
        for (int i = 0; i < fields.length; i++) {
            if (!java.lang.reflect.Modifier.isFinal(fields[i].getModifiers())
                    && !java.lang.reflect.Modifier.isStatic(fields[i]
                    .getModifiers())) {
                result.add(fields[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IllegalAccessException {
        Item item=new Item();
        populate(item);
        System.out.println(item);

    }
}
