/**MY COURSES*/
private static final String courseOutline="/course_outline.jsp";
private static final String examsByCourse="/course_exams.jsp";
private static final String pastCourses="/past_courses.jsp";
private static final String course="/course.jsp";
/**MY EXAMS*/
private static final String upcomingExams="/upcoming_exams.jsp";
private static final String pastExams="/past_exams.jsp";
private static final String exam="/exam.jsp";
/**MY PROFILE*/
private static final String viewProfile="/view_profile.jsp";
private static final String editProfile="/edit_profile.jsp";
private static final String changePassword="/change_password.jsp";

protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String forward="";
   	int accountID=session.getAttribute("accountID");
    String action=request.getParameter("action");

    /**MY COURSES*/
    if(action.equals("courseOutline")){
    	//get course outline by courseID
    	request.setAttribute("course_outline",course_outline);
    	forward=courseOutline;
    }else if(action.equals("examsByCourse")){
    	//get exams by courseID
    	request.setAttribute("exams",exams);
    	forward=examsByCourse;
    }else if(action.equals("pastCourses")){
    	//get past courses by accountID
    	request.setAttribute("courses",courses);
    	forward=pastCourses;
    }else if(action.equals("viewCourse")){
    	int courseID=Integer.parseInt(request.getParameter("courseID"));
    	CourseBean course=SystemDao.getCourseByID(courseID);
    	request.setAttribute("course",course);
    	forward=course;
    }

    /**MY EXAMS*/
    else if(action.equals("upcomingExams")||action.equals("pastExams")){
    	//get exams by accountID
    	request.setAttribute("exams",exams);
    	if(action.equals("upcomingExams"))
    		forward=upcomingExams;
    	else if(action.equals(pastExams)
    		forward=pastExams;
    }else if(action.equals("takeExam")||action.equals("viewExam")){
    	int examID=Integer.parseInt(request.getParameter("examID"));
    	ExamBean exam=SystemDao.getExamByID(examID);
    	request.setAttribute("exam",exam);
    	forward=exam;
    }

    /**MY PROFILE*/
    else if(action.equals("viewProfile")||action.equals("editProfile")
    	||action.equals("changePassword")){
    	AccountBean account=SystemDao.getAccountByID(accountID);
    	request.setAttribute("account",account);
    	if(action.equals("viewProfile"))
    		forward=viewProfile;
    	else if(action.equals("editProfile"))
    		forward=editProfile;
    	else if(action.equals("changePassword"))
    		forward=changePassword;
    }else if(action.equals("submitEditProfile")){
    	//update profile by accountID
    	forward=editProfile;
    }else if(action.equals("submitChangePassword")){
    	//update password by accountID
    	forward=changePassword;
    }

    RequestDispatcher rd=request.getRequestDispatcher(forward);
    rd.forward(request, response);
}