package fr.lernejo.tester.internal;
import fr.lernejo.tester.api.TestMethod;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class TestClassDescription {
    Class<?> tcd;

    public TestClassDescription(Class<?> arg){
        this.tcd = arg;
    }

    public List<Method> listTestMethods(){
        List<Method> listeDepart = List.of(this.tcd.getDeclaredMethods());
        List<Method> listeRetour = new ArrayList<>();

        for (Method method : listeDepart) {
            if (Modifier.isPublic(method.getModifiers())) {
                if (method.getReturnType().equals(Void.TYPE)) {
                    if (method.getParameterCount() == 0) {
                        if (method.isAnnotationPresent(TestMethod.class)) {
                            listeRetour.add(method);
                        }
                    }
                }
            }
        }

        return listeRetour;
    }
}
