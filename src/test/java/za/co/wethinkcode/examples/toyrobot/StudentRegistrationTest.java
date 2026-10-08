package za.co.wethinkcode.examples.toyrobot;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegistrationTest {

    @Test
    void studentStoresNameAndEmail() {
        Student student = new Student("Njabulo", "njabulo@example.com");

        assertEquals("Njabulo", student.getName());
        assertEquals("njabulo@example.com", student.getEmail());
    }

    @Test
    void studentStoresGeneratedDatabaseId() {
        Student student = new Student("Lerato", "lerato@example.com");

        student.setId(12);

        assertEquals(12, student.getId());
    }

    @Test
    void courseStoresNameAndGeneratedDatabaseId() {
        Course course = new Course("Software Engineering");

        course.setCourseId(7);

        assertEquals("Software Engineering", course.getCourseName());
        assertEquals(7, course.getCourseId());
    }

    @Test
    void registrationLinksStudentAndCourseWithSpecifiedDate() {
        Student student = new Student("Njabulo", "njabulo@example.com");
        Course course = new Course("Software Engineering");
        LocalDate date = LocalDate.of(2026, 10, 8);

        Registration registration = new Registration(student, course, date);

        assertSame(student, registration.getStudent());
        assertSame(course, registration.getCourse());
        assertEquals(date, registration.getDate());
    }

    @Test
    void registrationDefaultsToCurrentDate() {
        Student student = new Student("Lerato", "lerato@example.com");
        Course course = new Course("Accounting");

        LocalDate before = LocalDate.now();
        Registration registration = new Registration(student, course);
        LocalDate after = LocalDate.now();

        // Also handles the test running across midnight.
        LocalDate actual = registration.getDate();

        assertTrue(actual.equals(before) || actual.equals(after));
    }

    @Test
    void sameStudentCanHaveRegistrationsForDifferentCourses() {
        Student student = new Student("Njabulo", "njabulo@example.com");
        Course accounting = new Course("Accounting");
        Course software = new Course("Software Engineering");

        Registration first = new Registration(student, accounting);
        Registration second = new Registration(student, software);

        assertSame(student, first.getStudent());
        assertSame(student, second.getStudent());
        assertSame(accounting, first.getCourse());
        assertSame(software, second.getCourse());
    }
}