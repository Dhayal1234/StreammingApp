package org.example;
import java.util.*;

class Stream {
    int id;
    int viewers;
    String section;

    public Stream(int id, int viewers, String section) {
        this.id = id;
        this.viewers = viewers;
        this.section = section;
    }

    public String toString() {
        return "Stream{id=" + id + ", viewers=" + viewers + ", section=" + section + "}";
    }
}

public class StreamRecommendation {

    public static Map<String, List<Stream>> getTopUniqueStreamsPerSection(List<Stream> streams) {
        Map<String, List<Stream>> sectionToStreams = new HashMap<>();
        Set<Integer> usedStreamIds = new HashSet<>();

        // Group streams by section and sort each group by viewers descending
        Map<String, PriorityQueue<Stream>> sectionToSortedStreams = new HashMap<>();
        for (Stream s : streams) {
            sectionToSortedStreams
                    .computeIfAbsent(s.section, k -> new PriorityQueue<>((a, b) -> b.viewers - a.viewers))
                    .add(s);
        }

        // Keep trying to fill each section with top 3 unique streams
        boolean updated;
        do {
            updated = false;
            for (String section : sectionToSortedStreams.keySet()) {
                List<Stream> topList = sectionToStreams.computeIfAbsent(section, k -> new ArrayList<>());
                PriorityQueue<Stream> pq = sectionToSortedStreams.get(section);

                while (topList.size() < 3 && !pq.isEmpty()) {
                    Stream candidate = pq.poll();
                    if (usedStreamIds.add(candidate.id)) {
                        topList.add(candidate);
                        updated = true;
                    }
                }
            }
        } while (updated);

        return sectionToStreams;
    }

    public static void main(String[] args) {
        List<Stream> streams = Arrays.asList(
                new Stream(1, 500, "Gaming"),
                new Stream(2, 600, "Gaming"),
                new Stream(3, 700, "Gaming"),
                new Stream(4, 400, "Gaming"),
                new Stream(5, 300, "Music"),
                new Stream(6, 800, "Music"),
                new Stream(7, 500, "Music"),
                new Stream(8, 200, "Music"),
                new Stream(9, 750, "Tech"),
                new Stream(10, 650, "Tech"),
                new Stream(11, 550, "Tech"),
                new Stream(12, 450, "Tech")
        );

        Map<String, List<Stream>> result = getTopUniqueStreamsPerSection(streams);

        for (Map.Entry<String, List<Stream>> entry : result.entrySet()) {
            System.out.println("Section: " + entry.getKey());
            for (Stream s : entry.getValue()) {
                System.out.println("  " + s);
            }
        }
    }
}



