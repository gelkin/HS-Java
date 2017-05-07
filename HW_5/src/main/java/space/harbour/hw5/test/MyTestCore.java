package space.harbour.hw5.test;

import space.harbour.hw5.annotations.Before;
import space.harbour.hw5.annotations.Test;
import space.harbour.hw5.annotations.After;
import space.harbour.hw5.annotations.MyReflectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyTestCore {

    public static void runClasses(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            System.out.println("\n==============================\n");
            System.out.println("Tests for class " + clazz.getName() + " are running:");
            runClass(clazz);
        }
    }

    private static void runClass(Class<?> clazz) {
        Object classInstance = MyReflectionHelper.instantiate(clazz);
        try {
            // 'Before' annotation
            List<Method> beforeMethods = getMethodsAnnotatedWith(clazz, Before.class);
            for (Method method : beforeMethods) {
                MyReflectionHelper.callMethod(classInstance, method.getName());
            }

            // 'Test' annotation
            List<Method> testMethods = getMethodsAnnotatedWith(clazz, Test.class);
            for (Method method : testMethods) {
                MyReflectionHelper.callMethod(classInstance, method.getName());
            }

            // 'After' annotation
            List<Method> afterMethods = getMethodsAnnotatedWith(clazz, After.class);
            for (Method method : afterMethods) {
                MyReflectionHelper.callMethod(classInstance, method.getName());
            }

            System.out.println("All tests successfully passed!");

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (MyAssert.MyAssertionError e) {
            System.err.println("CAUGHT EXCEPTION:");
            e.printStackTrace();
        }
    }

    public static List<Method> getMethodsAnnotatedWith(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> allMethods = new ArrayList<>(Arrays.asList(clazz.getDeclaredMethods()));
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
