package com.strava;

import com.strava.models.Segment;

public class RecommendationModule {

  RankModule rankModule;

  public RecommendationModule(RankModule rankModule) {
    this.rankModule = rankModule;
  }

  public Segment getRecommendedSegment() {
    if(rankModule.getRankedSegments().size() > 0){
      return rankModule.getRankedSegments().get(0);
    }
    return new Segment();
  }
}
