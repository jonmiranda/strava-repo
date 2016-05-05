package com.strava;

import com.strava.models.Segment;

public class RecommendationModule {

  RankModule rankModule;

  public RecommendationModule(RankModule rankModule) {
    this.rankModule = rankModule;
  }

  public Segment getRecommendedSegment() {
    return rankModule.getRankedSegments().get(0);
  }
}
