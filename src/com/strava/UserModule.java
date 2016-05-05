package com.strava;

import com.strava.models.Activity;
import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.List;

public class UserModule {
  private final List<Activity> activities;
  private final List<Segment> starredSegments;
  private final List<Integer> climbingCategories; // List of climbing categories, ranked in order by preference

  private double averageSpeed = 3.0; // in meters per second

  public UserModule(List<Activity> activities, List<Segment> starredSegments) {
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

  private void buildProfile() {

  }
}
