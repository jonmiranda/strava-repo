package com.strava;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
        NetworkModule network = new NetworkModule();
        UserProfileModule userProfileModule = new UserProfileModule(network.getActivities(), network.getStarredSegments(11097));
        RankModule rankModule = new RankModule(network.getSegmentTargets(35.2725611, -120.7054915), userProfileModule);
        RecommendationModule recommendationModule = new RecommendationModule(rankModule);
        recommendationModule.getRecommendedSegment();

        recommendationModule.testSearch();

    }
}
