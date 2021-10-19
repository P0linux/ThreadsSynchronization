package ThirdTask;

import java.util.List;
import java.util.stream.Collectors;

public class Teacher implements Runnable
{
    private final String teacherName;
    private final int weeksCount;
    private final GroupJournal journal;


    public Teacher(String teacherName, int weeksCount, GroupJournal journal)
    {
        this.teacherName = teacherName;
        this.weeksCount = weeksCount;
        this.journal = journal;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < weeksCount; i++){
            for (String group : journal.groups.keySet().stream().sorted().collect(Collectors.toList())){
                for (Integer student : journal.groups.get(group).groupList.keySet()){
                    int mark = (int) Math.round(100 * Math.random());
                    journal.setMark(group, student, mark + " " + teacherName);
                }
            }
        }
    }
}
