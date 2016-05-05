package com.strava;

import com.strava.models.Activity;
import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Network module.
 */
public class NetworkModule {
  private List<Segment> segmentTargets;
  private List<Segment> starredSegments;
  private List<Activity> activities;

  public NetworkModule() {
    segmentTargets = new ArrayList<>();
    starredSegments = new ArrayList<>();
    activities = new ArrayList<>();
  }

  public List<Segment> getSegmentTargets() {
    return segmentTargets;
  }

  public List<Segment> getStarredSegments() {
    return starredSegments;
  }

  public List<Activity> getActivities() {
    return activities;
  }
}
