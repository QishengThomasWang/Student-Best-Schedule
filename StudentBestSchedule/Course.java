import java.util.ArrayList;

public class Course {
    private ArrayList<int []> lecture = new ArrayList<>();
    private String courseCode;

    private boolean are_same_lecture(int[] firstTuple, int[] secondTuple){
        if (firstTuple[0] == secondTuple[0] && firstTuple[1] == secondTuple[1]) {
            return true;
        }
        return false;
    }

    public Course(String courseCode){
        this.courseCode = courseCode;
    }

    public String getCode(){
        return courseCode;
    }

    public boolean hasLectureOn(int day) {
        for (int[] schedule : this.lecture) {
            if (schedule[0] == day) {
                return true;
            }
        }
        return false;
    }

    public void addLecture (int day, int hour){
        if (1 <= day && day <= 5 && 8 <= hour && hour <= 17){
            int[] newlecture = {day, hour};
            if (this.lecture.isEmpty()){
                this.lecture.add(newlecture);
            }
            else{
                for (int i = 0; i < this.lecture.size(); i++){
                    if (are_same_lecture(newlecture, this.lecture.get(i))){
                        return;
                    }
                }
                this.lecture.add(newlecture);
            }
        }
    }

    public boolean hasLectureAt(int day, int hour){
        for (int[] correspondingLecture : this.lecture) {
            if (correspondingLecture[0] == day && correspondingLecture[1] == hour) {
                return true;
            }
        }
        return false;
    }

    public boolean conflictsWith(Course other){
        for (int [] time : this.lecture){
            if(other.hasLectureAt(time[0], time[1])) {
                return true;
            }
        }
        return false;
    }
}