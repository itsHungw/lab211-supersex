package service;

import model.Mountain;
import model.Student;
import model.Statistic;

import java.util.*;

public class StatisticsService {

    public List<Statistic> statisticalizeByMountain(List<Student> students, List<Mountain> mountains) {
        Map<String, Statistic> map = new HashMap<>();

        // Initialize map with all mountains
        for (Mountain m : mountains) {
            map.put(m.getMountainCode(), new Statistic(m.getMountainCode(), m.getMountain()));
        }

        // Populate statistics from student data
        for (Student s : students) {
            Statistic info = map.get(s.getMountainCode());
            if (info != null) {
                info.addStudent(s.getTutionFee());
            }
        }

        List<Statistic> result = new ArrayList<>();
        for (Statistic stat : map.values()) {
            // Only include mountains with participants
            if (stat.getNumberOfParticipants() > 0) {
                result.add(stat);
            }
        }
        // Sort the result by number of participants (desc) and then by total tuition fee (desc)
        result.sort((s1, s2) -> {
            if (s2.getNumberOfParticipants() != s1.getNumberOfParticipants()) {
                return Integer.compare(s2.getNumberOfParticipants(), s1.getNumberOfParticipants());
            }
            return Double.compare(s2.getTotalTuitionFee(), s1.getTotalTuitionFee());
        });

        return result;
    }
}
