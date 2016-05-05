package com.strava;

import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.List;

public class RankModule {

  private final List<Segment> segmentTargets;
  List<Segment> rankedSegments;
  UserModule userModule;

  public RankModule(List<Segment> segmentTargets, UserModule userModule) {
    this.segmentTargets = segmentTargets;
    this.userModule = userModule;
    this.rankedSegments = new ArrayList<>();
    rank();
  }

  private void rank() {
    // TODO: Sam
    double averageSpeed = userModule.getAverageSpeed();
    List<Integer> climbingCategories = userModule.getClimbingCategories();
  }

  public List<Segment> getRankedSegments() {
    return rankedSegments;
  }
}
