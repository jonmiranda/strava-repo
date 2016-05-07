package com.strava;

import com.strava.models.Segment;

import java.util.ArrayList;
import java.util.List;

public class RankModule {

  private final List<Segment> segmentTargets;
  List<Segment> rankedSegments;
  UserProfileModule userProfileModule;

  public RankModule(List<Segment> segmentTargets, UserProfileModule userProfileModule) {
    this.segmentTargets = segmentTargets;
    this.userProfileModule = userProfileModule;
    this.rankedSegments = new ArrayList<>();
    rank();
  }

  private void rank() {
    // TODO: Sam
    double averageSpeed = userProfileModule.getAverageSpeed();
    List<Integer> climbingCategories = userProfileModule.getClimbingCategories();
  }

  public List<Segment> getRankedSegments() {
    return rankedSegments;
  }
}
