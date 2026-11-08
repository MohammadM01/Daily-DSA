SELECT
    S.student_id,
    S.student_name,
    SU.subject_name,
    COUNT(E.student_id) AS attended_exams
FROM Students S
CROSS JOIN Subjects SU
LEFT JOIN Examinations E
    ON E.student_id = S.student_id
    AND E.subject_name = SU.subject_name
GROUP BY 1, 2, 3
ORDER BY 1, 2, 3;