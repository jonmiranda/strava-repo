package com.strava;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
	    NetworkModule network = new NetworkModule();
      UserModule userModule = new UserModule(network.getActivities(), network.getStarredSegments(11097));
      RankModule rankModule = new RankModule(network.getSegmentTargets(35.2725611,-120.7054915), userModule);
      RecommendationModule recommendationModule = new RecommendationModule(rankModule);
      recommendationModule.getRecommendedSegment();
    }
}
