package com.strava;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
	    NetworkModule network = new NetworkModule();
      UserModule userModule = new UserModule(network.getActivities(), network.getStarredSegments());
      RankModule rankModule = new RankModule(network.getSegmentTargets(), userModule);
      RecommendationModule recommendationModule = new RecommendationModule(rankModule);
      recommendationModule.getRecommendedSegment();
    }
}
