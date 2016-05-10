package com.strava;

import com.strava.models.Activity;
import com.strava.models.Segment;
import com.strava.models.SegmentEffort;

import java.util.*;

public class UserProfileModule {
  private final List<Activity> activities;
  private final List<Segment> starredSegments;
  private final List<Integer> climbingCategories; // List of climbing categories, ranked in order by preference

  private double averageSpeed = 3.0; // in meters per second

  public UserProfileModule(List<Activity> activities, List<Segment> starredSegments) {
    this.activities = activities;
    this.starredSegments = starredSegments;
    this.climbingCategories = new ArrayList<>();
    buildProfile();
  }

  public double getAverageSpeed() {
    return averageSpeed;
  }

  /**
   * Returns a list of unique climbing categories, ranked in order by user history.
   *
   * For example, if the user's last three activities were: 2, 2, 1, then this list will return [2, 1].
   * @return
   */
  public List<Integer> getClimbingCategories() {
    return climbingCategories;
  }

  public double getClimbingCategoryAverage() {
    return 0.0;
//    return climbingCategories.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
  }

  public static List<Integer> sortByMostCommon(List<Integer> integers) {
    HashMap<Integer, Integer> valueToCount = new HashMap<>();
    for (Integer i : integers) {
      if (i == null) continue;
      int newValue = valueToCount.containsKey(i)
          ? valueToCount.get(i) + 1
          : 1;
      valueToCount.put(i, newValue);
    }

    LinkedHashMap<Integer, Integer> sorted = sortHashMapByValues(valueToCount);
    List<Integer> sortedList = new ArrayList<>();

    for (Integer i : sorted.keySet()) {
      sortedList.add(0, i);
    }

    return sortedList;
  }

  private void buildProfile() {
    List<Integer> tempClimbingCategories = new ArrayList<>();

    for (Activity activity: activities) {
      for (SegmentEffort effort : activity.getSegmentEfforts()) {
        tempClimbingCategories.add(effort.getClimbCategory());
      }
    }

    for (Segment segment : starredSegments) {
      tempClimbingCategories.add(segment.getClimbCategory());
    }

    climbingCategories.addAll(sortByMostCommon(tempClimbingCategories));
  }

  /**
   * http://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
   */
  private static LinkedHashMap<Integer, Integer> sortHashMapByValues(HashMap<Integer, Integer> passedMap) {
    List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
    List<Integer> mapValues = new ArrayList<>(passedMap.values());
    Collections.sort(mapValues);
    Collections.sort(mapKeys);

    LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

    Iterator<Integer> valueIt = mapValues.iterator();
    while (valueIt.hasNext()) {
      Integer val = valueIt.next();
      Iterator<Integer> keyIt = mapKeys.iterator();

      while (keyIt.hasNext()) {
        Integer key = keyIt.next();
        Integer comp1 = passedMap.get(key);
        Integer comp2 = val;

        if (comp1.equals(comp2)) {
          keyIt.remove();
          sortedMap.put(key, val);
          break;
        }
      }
    }
    return sortedMap;
  }
}
