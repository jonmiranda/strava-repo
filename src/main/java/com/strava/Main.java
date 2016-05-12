package com.strava;

import com.strava.models.Segment;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
        NetworkModule network = new NetworkModule();
        UserProfileModule userProfileModule = new UserProfileModule(network.getActivities(), network.getStarredSegments(11097));
        RankModule rankModule = new RankModule(userProfileModule);
        rankModule.rank(network.getSegmentTargets(35.2725611, -120.7054915));

        for (Segment segment : rankModule.getRankedSegments()) {
          System.out.println(String.format("Segment %s [estimated duration=%s, cc=%s]",
              segment.rank,
              segment.estimatedDuration,
              segment.climbingCategory));
        }

        RecommendationModule recommendationModule = new RecommendationModule(rankModule);
        System.out.println(recommendationModule.getRecommendedSegment());
    }
}
