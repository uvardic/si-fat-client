package fat.client.observer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Observer {

    private static final Set<Subject> SUBJECTS = new HashSet<>();

    private Observer() {}

    public static void addSubject(Subject subject) {
        if (subject == null)
            throw new NullPointerException("Subject can't be null!");

        SUBJECTS.add(subject);
    }

    @SafeVarargs
    public static void updateSubjects(Class<? extends Subject>... subjectClasses) {
        Arrays.stream(subjectClasses).forEach(Observer::updateSubject);
    }

    public static void updateSubject(Class<? extends Subject> subjectClass) {
        SUBJECTS.stream()
                .filter(subject -> subject.getClass().equals(subjectClass))
                .forEach(Subject::update);
    }

}
