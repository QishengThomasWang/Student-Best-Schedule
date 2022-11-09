import java.util.ArrayList;

public class Student {
    private String name;
    private ArrayList<Course> desiredCourses = new ArrayList<>();

    public Student (String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addDesiredCourse(Course courseName){
        if (!desiredCourses.contains(courseName)){
            desiredCourses.add(courseName);
        }
        else{
            return;
        }
    }

    public boolean validSchedule(ArrayList<Course> schedule) {
        if (schedule.size() != 5){
            return false;
        }
        else{
            for (int i = 0; i < schedule.size(); i++) {
                for (int j = i+1; j < schedule.size(); j++) {
                    if (!this.desiredCourses.contains(schedule.get(i)) || (schedule.get(i).conflictsWith(schedule.get(j)))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static ArrayList<ArrayList<Course>> powerSet(ArrayList<Course> course) {
        ArrayList<ArrayList<Course>> scheduleSubset = new ArrayList<>();
        int maximum = 1 << course.size();
        for (int i = 0; i < maximum; i++) {
            ArrayList<Course> subset = new ArrayList<>();
            for (int j = 0; j < course.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subset.add(course.get(j));
                }
            }
            scheduleSubset.add(subset);
        }
        return scheduleSubset;
    }

    public int dayCount(ArrayList<Course> currentSchedule){
        ArrayList<Integer> onCampusDays = new ArrayList<>();
        for(Course courseName: currentSchedule){
            for (int i = 1; i <= 5; i++){
                if (courseName.hasLectureOn(i) && !(onCampusDays.contains(i))){
                    onCampusDays.add(i);
                }
            }
        }
        return onCampusDays.size();
    }

    public ArrayList<Course> getBestSchedule(){
        ArrayList<ArrayList<Course>> scheduleSubset = powerSet(this.desiredCourses);
        ArrayList<Course> optimalSchedule = new ArrayList<>();
        int minimum = 5;
        for (ArrayList<Course> schedule : scheduleSubset) {
            if (validSchedule(schedule) == true) {
                if (dayCount(schedule) < minimum) {
                    minimum = dayCount(schedule);
                    optimalSchedule = schedule;
                }
            }
        }
        if (optimalSchedule.isEmpty()){
            return null;
        }
        return optimalSchedule;
    }
}