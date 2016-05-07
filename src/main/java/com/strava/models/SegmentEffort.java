package com.strava.models;

public class SegmentEffort {
  private Integer climbingCategory;

  public SegmentEffort(Integer climbingCategory) {
    this.climbingCategory = climbingCategory;
  }

  public Integer getClimbCategory() {
    return climbingCategory;
  }
}
