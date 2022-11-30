package net.javaguides.sms.controller;

import net.javaguides.sms.entity.Course;
import net.javaguides.sms.entity.Teacher;
import net.javaguides.sms.service.CourseService;
import net.javaguides.sms.service.TeacherService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

@org.springframework.stereotype.Controller
public class Controller {
	
	private StudentService studentService;
	private TeacherService teacherService;
	private CourseService courseService;

	public Controller(StudentService studentService, TeacherService teacherService, CourseService courseService) {
		super();
		this.studentService = studentService;
		this.teacherService = teacherService;
		this.courseService = courseService;
	}

	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/teachers")
	public String listTeachers(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeacher());
		return "teachers";
	}

	@GetMapping("/courses")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		return "courses";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {

		Student student = new Student();
		model.addAttribute("student", student);
		return "create_student";
		
	}

	@GetMapping("/teachers/new")
	public String createTeacherForm(Model model) {

		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "create_teacher";

	}
	@GetMapping("/courses/new")
	public String createCourseForm(Model model) {

		Course course = new Course();
		model.addAttribute("course", course);
		return "create_course.html";

	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	@PostMapping("/teachers")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
		teacherService.saveTeacher(teacher);
		return "redirect:/teachers";
	}
	@PostMapping("/courses")
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.saveCourse(course);
		return "redirect:/courses";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	@GetMapping("/teachers/edit/{id}")
	public String editTeacherForm(@PathVariable Long id, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(id));
		return "edit_teacher";
	}
	@GetMapping("/courses/edit/{id}")
	public String editCourseForm(@PathVariable Long id, Model model) {
		model.addAttribute("course", courseService.getCourseById(id));
		return "edit_course";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {

		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());

		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
	}
	@PostMapping("/teachers/{id}")
	public String updateTeacher(@PathVariable Long id,
								@ModelAttribute("teacher") Teacher teacher,
								Model model) {

		Teacher existingTeacher = teacherService.getTeacherById(id);
		existingTeacher.setId(id);
		existingTeacher.setFirstName(teacher.getFirstName());
		existingTeacher.setLastName(teacher.getLastName());
		existingTeacher.setEmail(teacher.getEmail());

		teacherService.updateTeacher(existingTeacher);
		return "redirect:/teachers";
	}
	@PostMapping("/courses/{id}")
	public String updateCourse(@PathVariable Long id,
								@ModelAttribute("course") Course course,
								Model model) {

		Course existingCourse = courseService.getCourseById(id);
		existingCourse.setId(id);
		existingCourse.setName(course.getName());
		existingCourse.setDescription(course.getDescription());

		courseService.updateCourse(existingCourse);
		return "redirect:/courses";
	}

	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
	@GetMapping("/teachers/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teachers";
	}
	@GetMapping("/courses/{id}")
	public String deleteCourse(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return "redirect:/courses";
	}
	
}
