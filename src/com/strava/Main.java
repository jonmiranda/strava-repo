package com.strava;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
	    NetworkModule network = new NetworkModule();
      UserProfileModule userProfileModule = new UserProfileModule(network.getActivities(), network.getStarredSegments());
      RankModule rankModule = new RankModule(network.getSegmentTargets(), userProfileModule);
      RecommendationModule recommendationModule = new RecommendationModule(rankModule);
      recommendationModule.getRecommendedSegment();
    }
}
