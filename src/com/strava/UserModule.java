package com.strava;

import com.strava.models.Activity;
import com.strava.models.Segment;

import java.util.List;

public class UserModule {
  private final List<Activity> activities;
  private final List<Segment> starredSegments;

  public UserModule(List<Activity> activities, List<Segment> starredSegments) {
    this.activities = activities;
    this.starredSegments = starredSegments;
  }
}
