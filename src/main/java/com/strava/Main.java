package com.strava;

import com.strava.models.Segment;

/**
 * Command line interface.
 */
public class Main {

    public static void main(String[] args) {
        NetworkModule network = new NetworkModule();
        UserProfileModule userProfileModule = new UserProfileModule(network.getActivities(), network.getStarredSegments(4555771));
        RankModule rankModule = new RankModule(userProfileModule);
        rankModule.rank(network.getSegmentTargets(35.2725611, -120.7054915));

        System.out.println(String.format("Users preferes cc=%1$.0f, planned duration=%2$dmins",
                userProfileModule.getClimbingCategoryAverage(),
                900 / 60)
        );

        for (Segment segment : rankModule.getRankedSegments()) {
          System.out.println(String.format("Segment rating %1$.2f [estimated duration=%2$.0fmins, cc=%3$d]",
              segment.rank,
              segment.estimatedDuration / 60,
              segment.climbingCategory));
        }

//        RecommendationModule recommendationModule = new RecommendationModule(rankModule);
//        System.out.println(recommendationModule.getRecommendedSegment());
    }
}
