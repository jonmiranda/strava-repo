package com.strava.models;

import java.util.List;

public class Activity {
  private List<SegmentEffort> segmentEfforts;

  public Activity(List<SegmentEffort> segmentEfforts) {
    this.segmentEfforts = segmentEfforts;
  }

  public List<SegmentEffort> getSegmentEfforts() {
    return segmentEfforts;
  }
}
