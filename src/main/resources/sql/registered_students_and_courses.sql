
SELECT students.name, courses.course_name, registrations.registration_date
FROM registrations
JOIN students ON students.student_id = registrations.student_id
JOIN  courses ON courses.course_id = registrations.course_id
