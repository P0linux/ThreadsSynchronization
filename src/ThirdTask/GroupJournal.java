package ThirdTask;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GroupJournal
{
    public ConcurrentHashMap<String, Group> groups = new ConcurrentHashMap<>();

    public GroupJournal()
    {
        Group firstGroup = new Group("FirstGroup", 30);
        Group secondGroup = new Group("SecondGroup", 25);
        Group thirdGroup = new Group("ThirdGroup", 27);

        groups.put(firstGroup.getName(), firstGroup);
        groups.put(secondGroup.getName(), secondGroup);
        groups.put(thirdGroup.getName(), thirdGroup);
    }

    public void setMark(String groupName, Integer student, String mark)
    {
        var studentMarks = groups.get(groupName).groupList.get(student);

        synchronized (Collections.unmodifiableList(studentMarks)){
            studentMarks.add(mark);
        }
    }

    public void print()
    {
        for (String group : groups.keySet().stream().sorted().collect(Collectors.toList())){
            System.out.printf("Group name: %6s\n", group);

            for (Integer student : groups.get(group).groupList.keySet().stream().sorted().collect(Collectors.toList())){
                System.out.printf("Student %5s", student);

                for (String mark : groups.get(group).groupList.get(student)){
                    System.out.printf("%30s", mark);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
